package com.example.demo.ide.Infrastructure.Controllers.Project;

import com.example.demo.ide.Infrastructure.Controllers.Controller;
import com.example.demo.ide.UI.Scenes.Welcome.Fork;
import javafx.fxml.FXML;
import org.springframework.stereotype.Component;

@Component
public class ComposerPackageController extends Controller {
    @FXML
    private String name;

    @FXML
    private String path;

    @FXML
    public void comeBack() {
        this.stage.switchScene(this.context.getBean(Fork.class).load());
    }
}
