package com.example.demo.ide.Domain.Editor.Entities;

import com.example.demo.ide.Domain.Editor.Entities.Files.File;

public class Tab {
    private final File file;

    public Tab(File file) {
        this.file = file;
    }

    public String name() {
        return this.file.name();
    }

    public String toString() {
        return this.file.path();
    }
}
