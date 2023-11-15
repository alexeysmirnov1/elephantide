package com.example.demo.ide.Infrastructure.Controllers.Project;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class VcsController extends AbstractProjectController {
    @FXML
    public TextField vcs;

    public void initialize() {
        this.createButton.setDisable(true);
    }

    public void create() {

    }

    public void validate() {
        boolean validName = !this.vcs.getText().isEmpty();
        boolean validPath = !this.path.getText().isEmpty();

        if (validName && validPath) {
            this.createButton.setDisable(false);
        }
    }
}
