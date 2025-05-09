package com.example.demo.git.Presentation.Controllers;

import com.example.demo.git.Infrastructure.Commands.Checkout;
import com.example.demo.git.Infrastructure.Commands.Commit;
import com.example.demo.git.Infrastructure.Commands.Init;
import com.example.demo.git.Infrastructure.Commands.Push;
import com.example.demo.git.Infrastructure.Repositories.GitRepository;
import com.example.demo.git.Presentation.Views.GitView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class GitViewModel extends GitView {
    private final String gitPath = "/root/projects/elephant/testgit";

    private GitRepository repository;

    @FXML
    public void initialize() {
        this.repository = new GitRepository(this.gitPath + "/.git");
    }

    @FXML
    private void initGit() {
        Init.run(this.gitPath);
    }

    @FXML
    private void gitCommit() {
        Commit commit = new Commit(this.gitPath + "/.git");

        ArrayList<String> files = new ArrayList<>();
        files.add("app.php");
        files.add("add.php");

        commit.run("start", files, true);
        System.out.println("create commit");
    }

    @FXML
    private void gitStatus() {
        System.out.println("check status");
        this.repository.status();
    }

    @FXML
    private void gitCheckout() {
        System.out.println("checkout");
        Checkout go = new Checkout(this.gitPath);
        go.run();
    }

    @FXML
    private void gitMerge() {
//        Init.run(this.gitPath);
    }

    @FXML
    private void gitPush() {
        Push push = new Push(this.gitPath);
        push.run();
        System.out.println("pushed");
    }

    @FXML
    private void gitLog() {
        System.out.println("git log");
        this.repository.getLog();
    }

    public void gitBlame() {
        this.repository.blame();
    }
}
