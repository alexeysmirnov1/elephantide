package com.example.demo.ide.UI.Scenes.Project;

import com.example.demo.ide.UI.Scene;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class Laravel extends Scene {
    public Laravel(@Value("classpath:/view/project/laravel-project.fxml") Resource resource) {
        this.fxml = resource;
        this.width = 1000;
        this.height = 700;
    }

    @Override
    public void afterCreating(javafx.scene.Scene scene) {
        scene.setFill(Color.TRANSPARENT);
    }
}
