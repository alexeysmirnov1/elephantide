package com.example.demo.git.Presentation.Views;

import com.example.demo.git.Domain.Entities.CommitingFile;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class GitView {
    @FXML
    private VBox filesForCommit;

    @FXML
    private VBox branches;

    @FXML
    private TextField commitMessage;

    protected void showFilesForNewCommit(ArrayList<CommitingFile> files) {
        this.filesForCommit.getChildren().clear();

        for (CommitingFile file: files) {
            CheckBox checkBox = new CheckBox(file.fileName);
            checkBox.setTextFill(Paint.valueOf(file.status.color()));
            this.filesForCommit.getChildren().add(checkBox);
        }
    }

    protected ArrayList<String> getCheckedFiles() {
        ArrayList<String> files = new ArrayList<>();

        for (var child: this.filesForCommit.getChildren()) {
            CheckBox checkBox = (CheckBox) child;
            if(checkBox.isSelected()) {
                files.add(checkBox.getText());
            }
        }

        return files;
    }

    protected String getCommitMessage() {
        return this.commitMessage.getText();
    }

    protected void setBranches(ArrayList<String> branches) {
        this.branches.getChildren().clear();

        for (String branch: branches) {
            Label b = new Label();
            b.setText(branch);
            b.setTextFill(Paint.valueOf("white"));
            this.branches.getChildren().add(b);
        }
    }
}
