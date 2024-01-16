package com.example.demo.ide.Presentation.Editor.UI.Scenes;

import com.example.demo.ide.Domain.Editor.Entities.Project;
import com.example.demo.ide.Infrastructure.Controllers.Editor.EditorViewModel;
import com.example.demo.ide.UI.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component
public class Editor extends Scene {
    private String projectPath;

    public Editor(@Value("classpath:/view/editor/index.fxml") Resource resource) {
        this.fxml = resource;
        this.width = 1300;
        this.height = 800;
    }

    public Editor project(String projectPath) {
        this.projectPath = projectPath;
        return this;
    }

    public javafx.scene.Scene load() {
        try {
            URL url = this.fxml.getURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);

            EditorViewModel controller = this.context.getBean(EditorViewModel.class);
            controller.project(new Project(this.projectPath));
            fxmlLoader.setControllerFactory(requiredType -> controller);

            Parent root = fxmlLoader.load();

            javafx.scene.Scene scene = new javafx.scene.Scene(root, this.width, this.height);

            this.afterCreating(scene);

            return scene;
        } catch (
            IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void afterCreating(javafx.scene.Scene scene) {
        scene.getStylesheets().add(
            Editor.class.getClassLoader().getResource("css/phptokens.css").toExternalForm()
        );
    }
}
