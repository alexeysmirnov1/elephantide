package com.example.demo.ide.Infrastructure.Controllers;

import com.example.demo.ide.UI.Scene;
import com.example.demo.ide.UI.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

public class Controller {
    @Autowired
    protected ConfigurableApplicationContext context;

    @Autowired
    protected Stage stage;

    protected javafx.scene.Scene loadScene(Class<Scene> aclass) {
        return this.context.getBean(aclass).load();
    }

    protected javafx.stage.Stage stage() {
        return this.context.getBean(javafx.stage.Stage.class);
    }
}
