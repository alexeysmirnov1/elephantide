package com.example.demo.ide.Presentation.Project.UI.Scenes;

import com.example.demo.ide.UI.Scene;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class Choose extends Scene {
    public Choose(@Value("classpath:/view/project/choose-type.fxml") Resource resource) {
        this.fxml = resource;
        this.width = 1000;
        this.height = 700;
    }

    @Override
    public void afterCreating(javafx.scene.Scene scene) {
        scene.setFill(Color.TRANSPARENT);
    }
}
