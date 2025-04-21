package com.example.demo;

import com.example.demo.ide.editor.PhpActorClient;
import com.example.demo.ide.editor.PhpActorLauncher;
import org.eclipse.lsp4j.ClientCapabilities;
import org.eclipse.lsp4j.InitializeParams;
import org.eclipse.lsp4j.TextDocumentClientCapabilities;
import org.eclipse.lsp4j.WorkspaceClientCapabilities;
import org.eclipse.lsp4j.jsonrpc.Launcher;
import org.eclipse.lsp4j.services.LanguageServer;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class DemoLSP {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        System.out.println(235);

        var phpActorLauncher = new PhpActorLauncher();
        phpActorLauncher.start();

        Launcher<LanguageServer> launcher = new Launcher.Builder<LanguageServer>()
            .setLocalService(new PhpActorClient())
            .setRemoteInterface(LanguageServer.class)
            .setInput(phpActorLauncher.getProcess().getInputStream())
            .setOutput(phpActorLauncher.getProcess().getOutputStream())
            .create();

        var server = launcher.getRemoteProxy();
        launcher.startListening();

        InitializeParams params = new InitializeParams();
        params.setRootUri("c:\\OSPanel\\domains\\site");

        ClientCapabilities clientCaps = new ClientCapabilities();
        clientCaps.setTextDocument(new TextDocumentClientCapabilities());
        clientCaps.setWorkspace(new WorkspaceClientCapabilities());
        params.setCapabilities(clientCaps);

        server.initialize(params).get();


    }
}
