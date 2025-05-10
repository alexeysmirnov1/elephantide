package com.example.demo.git.Infrastructure.Commands;

import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.IOException;

public class Checkout extends GitCommand {
    public Checkout(String gitPath) {
        super(gitPath);
    }

    public void run() {
        try {
            this.repository()
                    .checkout()
                    .setName("new")
                    .setCreateBranch(true)
                    .call();
        } catch (GitAPIException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
