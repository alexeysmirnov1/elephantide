package com.example.demo.git.Infrastructure.Commands;

import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import java.io.IOException;
import java.util.ArrayList;

public class Commit extends GitCommand {
    public Commit(String gitPath) {
        super(gitPath);
    }

    public void run(String message) {
        this.run(message, new ArrayList<>(), false);
    }

    public void run(String message, ArrayList<String> files, boolean amend) {
        try {
            org.eclipse.jgit.api.Git git = this.repository();

            var add = git.add();
            for(String file: files) {
                add.addFilepattern(file);
            }
            add.call();

            CommitCommand commit = git.commit();
            commit.setMessage(message);
            commit.setAll(true);
            commit.setAmend(amend);
            commit.call();
        } catch (IOException | GitAPIException e) {
            //todo CommitException
            throw new RuntimeException(e);
        }
    }
}
