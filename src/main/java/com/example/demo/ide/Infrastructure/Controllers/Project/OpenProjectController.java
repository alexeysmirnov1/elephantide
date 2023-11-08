package com.example.demo.ide.Infrastructure.Controllers.Project;

import com.example.demo.ide.Infrastructure.Controllers.Controller;
import com.example.demo.ide.UI.Scenes.Editor.Editor;
import com.example.demo.ide.UI.Scenes.Starting.Fork;
import javafx.fxml.FXML;
import org.springframework.stereotype.Component;

@Component
public class OpenProjectController extends Controller {
    @FXML
    public void comeBack() {
        this.stage.switchScene(this.context.getBean(Fork.class).load());
    }

    @FXML
    public void open() {
        this.stage.switchScene(this.context.getBean(Editor.class).load());
    }
}
