package com.example.demo.ide.editor;

import org.eclipse.lsp4j.*;
import org.eclipse.lsp4j.jsonrpc.Launcher;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.lsp4j.services.LanguageServer;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

public class PhpActorClient implements LanguageClient {
    private LanguageServer server;

    @Override
    public void telemetryEvent(Object object) {}

    public void connect(InputStream in, OutputStream out) {
        Launcher<LanguageServer> launcher = Launcher.createLauncher(
            this,
            LanguageServer.class,
            in,
            out
        );

        server = launcher.getRemoteProxy();
        server.initialize(new InitializeParams())
            .thenRun(() -> {
                server.initialized(new InitializedParams());
            })
            .thenAccept(result -> {
                System.out.println("LSP initialized: " + result.toString());
            })
            .exceptionally(ex -> {
                System.err.println("LSP init failed: " + ex.getMessage());
                ex.printStackTrace();
                return null;
            });
    }

    @Override
    public void publishDiagnostics(PublishDiagnosticsParams params) {
        // Обработка ошибок
        System.out.println("error");
    }

    @Override
    public void showMessage(MessageParams params) {
        // Показ сообщений от сервера
        System.out.println("result");
    }

    @Override
    public CompletableFuture<MessageActionItem> showMessageRequest(ShowMessageRequestParams showMessageRequestParams) {
        return null;
    }

    @Override
    public void logMessage(MessageParams messageParams) {

    }

//    @Override
    public CompletableFuture<InitializeResult> initialize(InitializeParams params) {
        // Явно задаем capabilities клиента
        ClientCapabilities capabilities = new ClientCapabilities();
        capabilities.setTextDocument(new TextDocumentClientCapabilities());
        capabilities.setWorkspace(new WorkspaceClientCapabilities());

        params.setCapabilities(capabilities);
        params.setRootUri(Paths.get("").toAbsolutePath().toUri().toString());

        // Возвращаем фиктивный результат
        return CompletableFuture.completedFuture(
            new InitializeResult(new ServerCapabilities())
        );
    }
}
