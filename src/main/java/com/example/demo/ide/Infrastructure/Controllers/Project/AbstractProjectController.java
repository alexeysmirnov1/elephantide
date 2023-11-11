package com.example.demo.ide.Infrastructure.Controllers.Project;

import com.example.demo.ide.Domain.Project.Factories.ProjectFactory;
import com.example.demo.ide.Infrastructure.Controllers.Controller;
import com.example.demo.ide.UI.Scenes.Starting.Fork;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.File;

abstract public class AbstractProjectController extends Controller {
    @Autowired
    protected ProjectFactory projectFactory;

    @FXML
    protected TextField name;

    @FXML
    protected TextField path;

    @FXML
    protected Button createButton;

    @FXML
    public void initialize() {
        this.createButton.setDisable(true);
    }

    @FXML
    abstract protected void create();

    @FXML
    protected void comeBack() {
        this.stage.switchScene(this.context.getBean(Fork.class).load());
    }

    @FXML
    protected void openFolderLocator() {
        DirectoryChooser loader = new DirectoryChooser();
        File directory = loader.showDialog(this.stage().getScene().getWindow());

        if (directory != null) {
            this.path.setText(directory.getPath());
            this.validate();
        }
    }

    @FXML
    protected void validate() {
        boolean validName = !this.name.getText().isEmpty();
        boolean validPath = !this.path.getText().isEmpty();

        if (validName && validPath) {
            this.createButton.setDisable(false);
        }
    }

    protected String projectPath() {
        return this.path + "/" + this.name;
    }
}
