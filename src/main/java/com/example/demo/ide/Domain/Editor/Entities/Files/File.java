package com.example.demo.ide.Domain.Editor.Entities.Files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public final class File {
    final private String path;

    public File(String path) {
        this.path = path;
    }

    public String path() {
        return this.path;
    }

    public String name() {
        java.io.File file = new java.io.File(this.path);
        return file.getName();
    }

    public String content() {
        try {
            return Files.readString(Path.of(this.path));
        } catch (IOException e) {
            return "";
        }
    }

    public void changeContent(String content) {
        try {
            Files.write(
                Path.of(this.path),
                content.getBytes(),
                StandardOpenOption.TRUNCATE_EXISTING
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
