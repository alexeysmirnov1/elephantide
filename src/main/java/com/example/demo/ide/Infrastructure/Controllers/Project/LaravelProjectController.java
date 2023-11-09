package com.example.demo.ide.Infrastructure.Controllers.Project;

import com.example.demo.ide.Infrastructure.Controllers.Controller;
import com.example.demo.ide.UI.Scenes.Starting.Fork;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class LaravelProjectController extends Controller {
    @FXML
    public TextField name;

    @FXML
    public TextField path;

    @FXML
    public Button createButton;

    @FXML
    public ChoiceBox<String> version;

    public void initialize() {
        this.createButton.setDisable(true);

        this.version.getItems().add("v10.0");
        this.version.getItems().add("v9.0");
        this.version.getItems().add("v8.0");
        this.version.setValue("v10.0");
    }

    @FXML
    public void comeBack() {
        this.stage.switchScene(this.context.getBean(Fork.class).load());
    }

    public void create() {}

    public void validate() {}

    public void openFolderLocator() {}
}
