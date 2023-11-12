package com.example.demo.ide.Infrastructure.Controllers.Project;

import com.example.demo.ide.Domain.Project.Entities.Project;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import org.springframework.stereotype.Component;

@Component
public class LaravelProjectController extends AbstractProjectController {
    @FXML
    private CheckBox git;

    @FXML
    public ChoiceBox<String> version;

    public void initialize() {
        super.initialize();

        this.version.getItems().add("v10.0");
        this.version.getItems().add("v9.0");
        this.version.getItems().add("v8.0");
        this.version.setValue("v10.0");
    }

    @FXML
    public void create() {
        Project project = this.projectFactory.laravel(
            this.projectPath(),
            this.version.getSelectionModel().getSelectedItem(),
            this.git.isSelected()
        );
    }
}
