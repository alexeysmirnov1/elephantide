package com.example.demo.ide.Domain.Editor.Entities;

import com.example.demo.ide.Domain.Editor.Entities.Files.File;

public class Tab {
    private final File file;

    private int cursorPosition = 0;
    private String content = null;

    public Tab(File file) {
        this.file = file;
    }

    public File file()
    {
        return this.file;
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
        if(this.content != null) {
            return this.content;
        }

        return this.file.content();
    }

    public int getCachedCursorPosition() {
        return this.cursorPosition;
    }
}
