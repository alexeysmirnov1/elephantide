package com.example.demo.ide.Infrastructure.Controllers.Starting;

import com.example.demo.ide.Infrastructure.Controllers.Controller;
import com.example.demo.ide.UI.Scenes.Project.Choose;
import com.example.demo.ide.UI.Scenes.Project.Open;
import javafx.fxml.FXML;
import org.springframework.stereotype.Component;

@Component
public class ForkController extends Controller {
    @FXML
    public void createNewProject() {
        this.stage.switchScene(this.context.getBean(Choose.class).load());
    }

    @FXML
    public void openExistsProject() {
        this.stage.switchScene(this.context.getBean(Open.class).load());
    }
}
