package com.example.demo.git.Infrastructure.Commands;

import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;

public class Clone {
    public static void run(String url, String path) {
        try {
            org.eclipse.jgit.api.Git.cloneRepository()
                    .setURI(url)
                    .setDirectory(new File(path))
                    .call();
        } catch (GitAPIException e) {
            //todo CloneException
            throw new RuntimeException(e);
        }
    }
}
