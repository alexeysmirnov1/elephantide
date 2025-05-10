package com.example.demo.git.Domain.Entities;

import com.example.demo.git.Domain.Values.GitStatus;

public class CommitingFile {
    public final String fileName;
    public final GitStatus status;

    public CommitingFile(String fileName, GitStatus status) {
        this.fileName = fileName;
        this.status = status;
    }
}
