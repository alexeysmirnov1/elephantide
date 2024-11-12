package com.example.demo.ide.Application.Git;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.File;
import java.io.IOException;

public class Git {
    public void commit(String message) {
        try {
            org.eclipse.jgit.api.Git git = new org.eclipse.jgit.api.Git(this.repository());
        } catch (IOException e) {
            //todo CommitException
            throw new RuntimeException(e);
        }
    }
    public void commitAmend(String message) {}
    public void push() {}
    public void fetch() {}
    public void rebase() {}
    public void checkout(String branch, boolean isNew) {}
    public void log(String branch) {}
    public void blame() {}
    public void tag() {}

    public void init(String path) {
        try {
            org.eclipse.jgit.api.Git.init()
                .setDirectory(new File(path))
                .call();
        } catch (GitAPIException e) {
            //todo InitException
            throw new RuntimeException(e);
        }
    }
    public void clone(String url, String path) {
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

    private Repository repository() throws IOException {
        return FileRepositoryBuilder
            .create(new File( "./.git"));
    }
}
