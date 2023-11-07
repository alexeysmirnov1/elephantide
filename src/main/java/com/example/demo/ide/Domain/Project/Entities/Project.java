package com.example.demo.ide.Domain.Project.Entities;

import com.example.demo.ide.Domain.Project.Contracts.ComposerJsonStubContract;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Project {

    private final File project;

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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
