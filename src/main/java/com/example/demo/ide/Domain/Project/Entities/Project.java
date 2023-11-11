package com.example.demo.ide.Domain.Project.Entities;

import com.example.demo.ide.Domain.Project.Contracts.ComposerJsonStubContract;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Project {

    private final File project;

    public Project(String path) {
        this.project = new File(path);
        this.project.mkdir();
    }

    public Project(File project) {
        this.project = project;
    }

    public void addComposer(ComposerJsonStubContract composerJsonStub) {
        try {
            Files.write(
                Path.of(this.project.getPath() + "/composer.json"),
                composerJsonStub.content().getBytes(),
                StandardOpenOption.CREATE
            );

            File dir = new File(this.project.getPath() + "/src");
            dir.mkdir();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createGit() {
        try {
            FileRepositoryBuilder
                .create(new File(this.project.getPath() + "/.git"))
                .create();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
