package com.example.demo.ide.Infrastructure.Controllers.Project;

import com.example.demo.ide.Domain.Project.Entities.Project;
import com.example.demo.ide.Presentation.Editor.UI.Scenes.Editor;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import org.springframework.stereotype.Component;

@Component
public class ComposerPackageController extends AbstractProjectController {
    @FXML
    private CheckBox git;

    @FXML
    public void create() {
        Project project = this.projectFactory.composerPackage(
            this.projectPath(),
            this.git.isSelected()
        );

        this.stage.switchScene(this.context.getBean(Editor.class).load());
    }
}
