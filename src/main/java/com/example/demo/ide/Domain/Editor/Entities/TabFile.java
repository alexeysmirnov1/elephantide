package com.example.demo.ide.Domain.Editor.Entities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class TabFile {
    private final String path;

    private String content;

    public TabFile(String path) {
        this.path = path;
    }

    public String content() {
        if(!this.content.isBlank()) {
            return this.content;
        }

        try {
            this.content = Files.readString(Path.of(this.path));
            return this.content;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeContent(String content) {
        try {
            Files.write(
                    Path.of(this.path),
                    content.getBytes(),
                    StandardOpenOption.TRUNCATE_EXISTING
            );
            this.content = content;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
