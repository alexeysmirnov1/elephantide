package com.example.demo.ide.Infrastructure.Controllers.Project;

import com.example.demo.ide.Infrastructure.Controllers.Controller;
import com.example.demo.ide.UI.Scenes.Starting.Fork;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ComposerPackageController extends Controller {
    @FXML
    private TextField name;

    @FXML
    private TextField path;

    @FXML
    private CheckBox git;

    @FXML
    private Button createButton;

    @FXML
    public void initialize() {
        this.createButton.setDisable(true);
    }

    @FXML
    public void comeBack() {
        this.stage.switchScene(this.context.getBean(Fork.class).load());
    }

    @FXML
    public void create() {
    }

    public void openFolderLocator() {
        DirectoryChooser loader = new DirectoryChooser();
        File directory = loader.showDialog(this.stage().getScene().getWindow());

        if (directory != null) {
            this.path.setText(directory.getPath());
            this.validate();
        }
    }

    public void validate() {
        boolean validName = !this.name.getText().isEmpty();
        boolean validPath = !this.path.getText().isEmpty();

        if (validName && validPath) {
            this.createButton.setDisable(false);
        }
    }
}
