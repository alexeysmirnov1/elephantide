package com.example.demo.ide.UI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Stage {
    @Autowired
    private ConfigurableApplicationContext context;

    public void switchScene(javafx.scene.Scene scene) {
        javafx.stage.Stage stage = this.context.getBean(javafx.stage.Stage.class);
        stage.setScene(scene);
        stage.centerOnScreen();
    }
}
