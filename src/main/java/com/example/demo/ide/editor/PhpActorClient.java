package com.example.demo.ide.editor;

import org.eclipse.lsp4j.*;
import org.eclipse.lsp4j.jsonrpc.Launcher;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.lsp4j.services.LanguageServer;
import java.io.InputStream;
import java.io.OutputStream;
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
        server.initialize(new InitializeParams()).thenRun(() -> {
            server.initialized(new InitializedParams());
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
}
