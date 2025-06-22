package com.example.demo.ide.Domain.Editor.Entities;

import com.example.demo.ide.Domain.Editor.Entities.Files.File;

public class Tab {
    private final File file;

    private int cursorPosition;
    private String content;

    public Tab(File file) {
        this.file = file;
    }

    public String name() {
        return this.file.name();
    }

    public String toString() {
        return this.file.path();
    }

    public void cacheContent(String content)
    {
        this.content = content;
    }

    public void cacheCursor(int cursorPosition)
    {
        this.cursorPosition = cursorPosition;
    }

    public String getCachedContent() {
        return this.content;
    }

    public int getCachedCursorPosition() {
        return this.cursorPosition;
    }
}
