package com.example.demo.ide.Infrastructure.Controllers.Project;

import com.example.demo.ide.Infrastructure.Controllers.Controller;
import com.example.demo.ide.UI.Scenes.Welcome.Fork;
import javafx.fxml.FXML;
import org.springframework.stereotype.Component;

@Component
public class LaravelProjectController extends Controller {
    @FXML
    public String name;

    @FXML
    public String path;

    @FXML
    public void comeBack() {
        this.stage.switchScene(this.context.getBean(Fork.class).load());
    }
}
