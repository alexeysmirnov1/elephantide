package com.example.demo.git.Infrastructure.Repositories;

import com.example.demo.git.Domain.Entities.CommitingFile;
import com.example.demo.git.Domain.Values.GitStatus;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.blame.BlameResult;
import org.eclipse.jgit.diff.RawText;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public ArrayList<CommitingFile> status() {
        Status status = null;

        try {
            status = git.status().call();
        } catch (GitAPIException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        ArrayList<CommitingFile> files = new ArrayList<>();

        for (String modified: status.getModified()) {
            System.out.println("Modified file: " + modified);
            files.add(new CommitingFile(
                    modified,
                    GitStatus.MODIFIED
            ));
        }
        for (String modified: status.getChanged()) {
            System.out.println("Modified file: " + modified);
            files.add(new CommitingFile(
                    modified,
                    GitStatus.MODIFIED
            ));
        }
        for (String modified: status.getAdded()) {
            System.out.println("added file: " + modified);
            files.add(new CommitingFile(
                    modified,
                    GitStatus.ADDED
            ));
        }
        for (String modified: status.getUntracked()) {
            System.out.println("Untracked file: " + modified);
            files.add(new CommitingFile(
                    modified,
                    GitStatus.UNTRACKED
            ));
        }
        for (String modified: status.getConflicting()) {
            System.out.println("Conflicting file: " + modified);
            files.add(new CommitingFile(
                    modified,
                    GitStatus.CONFLICT
            ));
        }
        for (String modified: status.getMissing()) {
            System.out.println("missing file: " + modified);
            files.add(new CommitingFile(
                    modified,
                    GitStatus.DELETED
            ));
        }
        for (String modified: status.getRemoved()) {
            System.out.println("missing file: " + modified);
            files.add(new CommitingFile(
                    modified,
                    GitStatus.DELETED
            ));
        }

        return files;
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

    public ArrayList<String> branches() {
        List<Ref> result = null;

        try {
            result = git.branchList().call();
        } catch (GitAPIException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        ArrayList<String> branches = new ArrayList<>();

        for (Ref ref: result) {
            String branchName = ref.getName();
            branches.add(branchName.substring(branchName.lastIndexOf("/") + 1));
        }

        return branches;
    }
}
