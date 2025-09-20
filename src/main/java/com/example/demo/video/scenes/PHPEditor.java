package com.example.demo.video.scenes;

import com.example.demo.ide.Presentation.Editor.UI.Scenes.Editor;
import com.example.demo.ide.UI.Scene;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class PHPEditor extends Scene {
    public PHPEditor(@Value("classpath:/view/video/index.fxml") Resource resource) {
        this.fxml = resource;
        this.width = 1200;
        this.height = 700;
    }

    @Override
    public void afterCreating(javafx.scene.Scene scene) {
        scene.setFill(Color.TRANSPARENT);

        scene.getStylesheets().add(
                Editor.class.getClassLoader().getResource("css/phptokens.css").toExternalForm()
        );
    }
}
