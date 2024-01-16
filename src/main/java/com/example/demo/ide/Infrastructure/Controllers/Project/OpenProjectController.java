package com.example.demo.ide.Infrastructure.Controllers.Project;

import com.example.demo.ide.Presentation.Editor.UI.Scenes.Editor;
import javafx.fxml.FXML;
import org.springframework.stereotype.Component;

@Component
public class OpenProjectController extends AbstractProjectController {
    @FXML
    public void initialize() {
    }

    @Override
    protected void create() {

    }

    @FXML
    public void open() {
        this.stage.switchScene(this.context.getBean(Editor.class)
            .project(this.path.getText())
            .load());
    }

    @FXML
    protected void validate() {}
}
