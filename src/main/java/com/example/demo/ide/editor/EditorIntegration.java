package com.example.demo.ide.editor;

import javafx.application.Platform;
import org.eclipse.lsp4j.*;
import org.eclipse.lsp4j.jsonrpc.Launcher;
import org.eclipse.lsp4j.services.LanguageServer;

import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.*;

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
                params.setRootUri(String.valueOf(Paths.get(".").toAbsolutePath()));
//                server.initialize(params).get();

                CompletableFuture<InitializeResult> initFuture = server.initialize(params);
                initFuture.get(5, TimeUnit.SECONDS);

                Platform.runLater(() -> {
                    // Обновление UI после инициализации
//                    statusLabel.setText("PHP Actor ready");
                    System.out.println("php actor ready");
                });

            } catch (TimeoutException e) {
//                Platform.runLater(() -> showError("Timeout starting PHP Actor"));
                System.out.println("Timeout starting PHP Actor");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void shutdown() {
        try {
            // 1. Отправить shutdown запрос LSP-серверу
            if (server != null) {
                server.shutdown().get(2, TimeUnit.SECONDS);
            }

            // 2. Принудительно завершить процесс
            if (launcher != null) {
                launcher.stop();
            }

            // 3. Закрыть ExecutorService
            executor.shutdownNow();

        } catch (Exception e) {
            System.err.println("Shutdown error: " + e.getMessage());
        }
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
    }
}
