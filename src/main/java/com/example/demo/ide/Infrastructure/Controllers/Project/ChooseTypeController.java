package com.example.demo.ide.Infrastructure.Controllers.Project;

import com.example.demo.ide.Infrastructure.Controllers.Controller;
import com.example.demo.ide.Presentation.Project.UI.Scenes.Composer;
import com.example.demo.ide.Presentation.Project.UI.Scenes.Empty;
import com.example.demo.ide.Presentation.Project.UI.Scenes.Laravel;
import com.example.demo.ide.Presentation.Project.UI.Scenes.Vcs;
import com.example.demo.ide.UI.Scenes.Starting.Fork;
import javafx.fxml.FXML;
import org.springframework.stereotype.Component;

@Component
public class ChooseTypeController extends Controller {
    @FXML
    public void createEmptyProject() {
        this.stage.switchScene(this.context.getBean(Empty.class).load());
    }

    @FXML
    public void createComposerPackage() {
        this.stage.switchScene(this.context.getBean(Composer.class).load());
    }

    @FXML
    public void createLaraveProject() {
        this.stage.switchScene(this.context.getBean(Laravel.class).load());
    }

    @FXML
    public void createFromVCS() {
        this.stage.switchScene(this.context.getBean(Vcs.class).load());
    }

    @FXML
    public void comeBack() {
        this.stage.switchScene(this.context.getBean(Fork.class).load());
    }


}
