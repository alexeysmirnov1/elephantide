package com.example.demo.git.Infrastructure.Repositories;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.blame.BlameResult;
import org.eclipse.jgit.diff.RawText;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import java.io.File;
import java.io.IOException;
import static org.hibernate.type.descriptor.java.DateTypeDescriptor.DATE_FORMAT;

public class GitRepository {
    private final Git git;

    public GitRepository(String gitPath) {
        Repository repository = null;
        try {
            repository = FileRepositoryBuilder
                .create(new File(gitPath));

            this.git = new Git(repository);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void status() {
        Status status = null;

        try {
            status = git.status().call();
        } catch (GitAPIException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        for (String modified: status.getModified()) {
            System.out.println("Modified file: " + modified);
        }
        for (String modified: status.getAdded()) {
            System.out.println("added file: " + modified);
        }
        for (String modified: status.getChanged()) {
            System.out.println("changed file: " + modified);
        }
        for (String modified: status.getUntracked()) {
            System.out.println("Untracked file: " + modified);
        }
        for (String modified: status.getRemoved()) {
            System.out.println("Removed file: " + modified);
        }
        for (String modified: status.getIgnoredNotInIndex()) {
            System.out.println("Ignored file: " + modified);
        }
        for (String modified: status.getConflicting()) {
            System.out.println("Conflicting file: " + modified);
        }
    }

    public void getLog() {
        Iterable<RevCommit> log;
        try {
            log = this.git.log().call();
        } catch (GitAPIException e) {
            throw new RuntimeException(e);
        }

        for (RevCommit commit: log) {
            System.out.println(commit.getShortMessage());
        }
    }

    public void fetch() {}

    public void tags() {}

    public void blame() {
        BlameResult result = null;

        try {
            result = this.git.blame().setFilePath("index.php").call();
        } catch (GitAPIException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        final RawText rawText = result.getResultContents();
        for (int i = 0; i < rawText.size(); i++) {
            final PersonIdent sourceAuthor = result.getSourceAuthor(i);
            final RevCommit sourceCommit = result.getSourceCommit(i);

            System.out.println(
                sourceAuthor.getName()
                + " - line " + (i + 1) + " "
                + ": " + rawText.getString(i)
            );
        }
    }
}
