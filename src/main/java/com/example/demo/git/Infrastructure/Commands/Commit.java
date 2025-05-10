package com.example.demo.git.Infrastructure.Commands;

import org.eclipse.jgit.api.errors.GitAPIException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Commit extends GitCommand {
    public Commit(String gitPath) {
        super(gitPath);
    }

    public void run(String message, ArrayList<String> files, boolean amend) {
        try {
            org.eclipse.jgit.api.Git git = this.repository();

            var add = git.add();
            for(String file: files) {
                add.addFilepattern(file);
            }
            add.call();

            try {
                Process process = new ProcessBuilder()
                        .command("bash", "-c", "git commit -m " + message + " -- " + String.join(" ", files))
                        .directory(new File(this.gitPath))
                        .start();
                try {
                    process.waitFor();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException | GitAPIException e) {
            //todo CommitException
            throw new RuntimeException(e);
        }
    }
}
