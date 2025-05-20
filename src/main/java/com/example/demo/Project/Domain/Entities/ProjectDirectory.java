package com.example.demo.Project.Domain.Entities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class ProjectDirectory {
    private final File originalDir;

    public ProjectDirectory(String filePath) {
        this.originalDir = new File(filePath);
    }

    public void delete() {
        this.originalDir.delete();
    }

    public void move(String newPath) {
        try {
            Files.move(Path.of(this.originalDir.getPath()), Path.of(newPath), REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void rename(String newName) {
        this.originalDir.renameTo(new File(newName));
    }

    public File[] getChildren() {
        return this.originalDir.listFiles();
    }
}
