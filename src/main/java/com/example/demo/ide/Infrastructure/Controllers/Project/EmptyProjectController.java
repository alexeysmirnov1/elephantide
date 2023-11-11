package com.example.demo.ide.Infrastructure.Controllers.Project;

import com.example.demo.ide.Domain.Project.Entities.Project;
import com.example.demo.ide.UI.Scenes.Editor.Editor;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import org.springframework.stereotype.Component;

@Component
public class EmptyProjectController extends AbstractProjectController {
    @FXML
    private CheckBox composer;

    @FXML
    private CheckBox git;

    @FXML
    public void create() {
        Project project = this.projectFactory.empty(
            this.projectPath(),
            this.composer.isSelected(),
            this.git.isSelected()
        );

        this.stage.switchScene(this.context.getBean(Editor.class).load());
    }
}
