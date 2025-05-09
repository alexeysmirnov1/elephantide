package com.example.demo.git.Infrastructure.Commands;

import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;

public class Init {
    public static void run(String path) {
        try {
            org.eclipse.jgit.api.Git.init()
                    .setDirectory(new File(path))
                    .call();
        } catch (GitAPIException e) {
            //todo InitException
            throw new RuntimeException(e);
        }
    }
}
