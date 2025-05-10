package com.example.demo.git.Infrastructure.Commands;

import java.io.File;
import java.io.IOException;

public class Push extends GitCommand {
    public Push(String gitPath) {
        super(gitPath);
    }

    public void  run() {
        try {
            Process process = new ProcessBuilder()
                .command("git", "push", "--set-upstream")
                .directory(new File(this.gitPath))
                .redirectErrorStream(true)
                .start();
            try {
                process.waitFor();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
