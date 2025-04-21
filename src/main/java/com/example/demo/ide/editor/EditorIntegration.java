package com.example.demo.ide.editor;

import javafx.application.Platform;
import org.eclipse.lsp4j.*;
import org.eclipse.lsp4j.jsonrpc.Launcher;
import org.eclipse.lsp4j.services.LanguageServer;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.*;

@Component
public class EditorIntegration {
    private ExecutorService executor = Executors.newCachedThreadPool();
    private PhpActorLauncher launcher;
    private LanguageServer server;
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private volatile ScheduledFuture<?> pendingUpdate;

    public void initialize() {
        executor.submit(() -> {
            try {
                launcher = new PhpActorLauncher();
                launcher.start();

                Launcher<LanguageServer> launcher = new Launcher.Builder<LanguageServer>()
                    .setLocalService(new PhpActorClient())
                    .setRemoteInterface(LanguageServer.class)
                    .setInput(this.launcher.getProcess().getInputStream())
                    .setOutput(this.launcher.getProcess().getOutputStream())
                    .create();

                server = launcher.getRemoteProxy();
                launcher.startListening();

                InitializeParams params = new InitializeParams();
                params.setRootUri("c:\\OSPanel\\domains\\site");

                ClientCapabilities clientCaps = new ClientCapabilities();
                clientCaps.setTextDocument(new TextDocumentClientCapabilities());
                clientCaps.setWorkspace(new WorkspaceClientCapabilities());
                params.setCapabilities(clientCaps);

                server.initialize(params).get();

                Platform.runLater(() -> {
                    // Обновление UI после инициализации
//                    statusLabel.setText("PHP Actor ready");
                });
                System.out.println("php actor ready");

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void onTextChanged(String fileUri, String newText) {
        DidChangeTextDocumentParams changeParams = new DidChangeTextDocumentParams(
            new VersionedTextDocumentIdentifier(fileUri, 1),
            List.of(new TextDocumentContentChangeEvent(newText))
        );
        server.getTextDocumentService().didChange(changeParams);
    }

    public void scheduleTextUpdate(String fileUri, String newText) {
        if (pendingUpdate != null) {
            pendingUpdate.cancel(false);
        }

        pendingUpdate = scheduler.schedule(() -> {
            try {
                DidChangeTextDocumentParams params = new DidChangeTextDocumentParams(
                    new VersionedTextDocumentIdentifier(fileUri, 1),
                    List.of(new TextDocumentContentChangeEvent(newText))
                );

                server.getTextDocumentService().didChange(params);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 300, TimeUnit.MILLISECONDS); // Задержка 300 мс

        System.out.println(pendingUpdate);
    }

    // todo метод для получения подсказок
    public void completion(String filePath) {
//        scheduler.schedule(() -> {
            Position position = new Position(24, 10); // Получаем текущую позицию курсора
            CompletionParams params = new CompletionParams(
//                new TextDocumentIdentifier("app\\Application.php"),
                new TextDocumentIdentifier(filePath),
                position
            );

            server.getTextDocumentService().completion(params)
                .thenAccept(completionList -> {
                        completionList.getLeft().forEach(item ->
                            System.out.println(" - " + item.getLabel()));
                        List<CompletionItem> items = completionList.getLeft();
                        System.out.println(params);
                        System.out.println(items);
//                    showCompletionPopup(items); // Показ popup с предложениями
                    }
                ).exceptionally(ex -> {
                    System.err.println("Completion error: " + ex.getMessage());
                    return null;
                });
//        }, 300, TimeUnit.MILLISECONDS);
    }

    private void handleCompletionResponse(CompletionList completionList) {
        System.out.println("=== Completion Results ===");
        System.out.println("Is incomplete: " + completionList.isIncomplete());
        System.out.println("Items count: " + completionList.getItems().size());

        completionList.getItems().forEach(item -> {
            System.out.println("Label: " + item.getLabel());
            System.out.println("Detail: " + item.getDetail());
            System.out.println("Kind: " + item.getKind());
            System.out.println("-----------------------");
        });
    }

    // todo метод для анализа
    public void syntaxErrors() {
        PublishDiagnosticsParams params = new PublishDiagnosticsParams();
        params.setUri("c:\\OSPanel\\domains\\site\\index.php");
        params.getDiagnostics().forEach(diagnostic -> {
            Range range = diagnostic.getRange();
            String message = diagnostic.getMessage();
            System.out.println(range + " -- " + message);
//            highlightError(range, message); // Ваш метод подсветки
        });
    }

    // todo поиск мест для быстрого перехода к классу или методу
    public void definitions() {
        Position position = new Position(1, 5);
        DefinitionParams params = new DefinitionParams(
            new TextDocumentIdentifier("c:\\OSPanel\\domains\\site\\index.php"),
            position
        );

        server.getTextDocumentService().definition(params)
            .thenAccept(locations -> {
                if (!locations.getRight().isEmpty()) {
                    LocationLink loc = locations.getRight().get(0);
                    System.out.println(loc);
//                    openFile(loc.getUri(), loc.getRange()); // Открытие файла
                }
            });
    }

    // todo Подсказки при наведении
    public void hover() {
        Position position = new Position(1, 5);
        HoverParams params = new HoverParams(
            new TextDocumentIdentifier("c:\\OSPanel\\domains\\site\\index.php"),
            position
        );

        server.getTextDocumentService().hover(params)
            .thenAccept(hover -> {
                if (hover != null) {
                    System.out.println(hover.getContents());
//                    showTooltip(hover.getContents(), e.getScreenX(), e.getScreenY());
                }
            });
    }

    // todo Рефакторинг
    public void rename(String newName) {
        Position position = new Position(1, 5);
        RenameParams params = new RenameParams(
            new TextDocumentIdentifier("c:\\OSPanel\\domains\\site\\index.php"),
            position,
            newName
        );

        server.getTextDocumentService().rename(params)
            .thenAccept(workspaceEdit -> {
                System.out.println(workspaceEdit.getChanges());
//                applyEdits(workspaceEdit.getChanges()); // Применение изменений
            });
    }

    // todo Поиск ссылок
    public void findReferences() {
        Position position = new Position(1, 5);
        ReferenceParams params = new ReferenceParams(
            new TextDocumentIdentifier("c:\\OSPanel\\domains\\site\\index.php"),
            position,
            new ReferenceContext(false) // includeDeclaration: false
        );

        server.getTextDocumentService().references(params)
            .thenAccept(locations -> {
                System.out.println(locations.get(0));
//                showReferencesInSidebar(locations); // Отображение в UI
            });
    }
}
