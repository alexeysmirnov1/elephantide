package com.example.demo.git.Infrastructure.Commands;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import java.io.File;
import java.io.IOException;

public abstract class GitCommand {
    protected final String gitPath;

    public GitCommand(String gitPath) {
        this.gitPath = gitPath;
    }

    protected Git repository() throws IOException {
        var repository = FileRepositoryBuilder
            .create(new File(this.gitPath));

        return Git.open(new File(this.gitPath));
    }
}
