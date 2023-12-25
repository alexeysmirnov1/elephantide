package com.example.demo.ide.Domain.Editor.Entities;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class Project {
    private final String path;

    private ArrayList<File> files = new ArrayList<>();

    public Project(String path) {
        this.path = path;
    }

    public String path() {
        return this.path;
    }

    public boolean hasReadme() {
        return false;
    }

    public ArrayList<File> files() {
        java.io.File currentDir = new java.io.File(this.path());
        ArrayList<File> directories = new ArrayList<>();
        ArrayList<File> files = new ArrayList<>();

        for (File file: Objects.requireNonNull(currentDir.listFiles())) {
            if (file.isDirectory()) {
                directories.add(file);
            } else {
                files.add(file);
            }
        }

        directories.addAll(files);

        return directories;
    }
}
