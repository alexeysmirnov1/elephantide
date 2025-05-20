package com.example.demo.Project.Domain.Entities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class ProjectFile {
    private final File originalFile;

    public ProjectFile(String filePath) {
        this.originalFile = new File(filePath);
    }

    public void delete() {
        this.originalFile.delete();
    }

    public void move(String newPath) {
        try {
            Files.move(Path.of(this.originalFile.getPath()), Path.of(newPath), REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void rename(String newName) {
        this.originalFile.renameTo(new File(newName));
    }
}
