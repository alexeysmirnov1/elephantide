package com.example.demo.ide.UI.Scenes.Editor;

import com.example.demo.ide.UI.Scene;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Editor extends Scene {
    public Editor(@Value("classpath:/view/editor/index.fxml") Resource resource) {
        this.fxml = resource;
        this.width = 1300;
        this.height = 800;
    }

    public void afterCreating(javafx.scene.Scene scene) {
        scene.getStylesheets().add(
            Editor.class.getClassLoader().getResource("css/phptokens.css").toExternalForm()
        );
    }
}
