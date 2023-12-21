package com.example.demo.ide.Domain.Editor.Entities.Files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Iterator;

public final class File {
    final private String path;

    private ArrayList<String> lines = new ArrayList<>();

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

    public ArrayList<String> lines() {
        if(this.lines.isEmpty()) {
            this.read();
        }

        return this.lines;
    }

    public String content() {
        return this.lines().toString();
    }

    public void changeContent(String content) {
        try {
            Files.write(
                Path.of(this.path),
                content.getBytes(),
                StandardOpenOption.TRUNCATE_EXISTING
            );
            this.lines.clear();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void read() {
        java.io.File file = new java.io.File(this.path);

        try (BufferedReader reader = new BufferedReader(new FileReader(file.getPath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                this.lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
