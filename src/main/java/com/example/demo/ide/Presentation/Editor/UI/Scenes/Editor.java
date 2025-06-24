package com.example.demo.ide.Presentation.Editor.UI.Scenes;

import com.example.demo.ide.Domain.Editor.Entities.Project;
import com.example.demo.ide.Infrastructure.Controllers.Editor.EditorViewModel;
import com.example.demo.ide.UI.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component
public class Editor extends Scene {
    private String projectPath;

    private EditorViewModel controller;

    public Editor(@Value("classpath:/view/editor/index.fxml") Resource resource) {
        this.fxml = resource;
        this.width = 1500.0;
        this.height = 1000.0;
    }

    public Editor project(String projectPath) {
        this.projectPath = projectPath;
        return this;
    }

    public javafx.scene.Scene load() {
        try {
            URL url = this.fxml.getURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);

            this.controller = this.context.getBean(EditorViewModel.class);
            this.controller.project(new Project(this.projectPath));
            fxmlLoader.setControllerFactory(requiredType -> this.controller);

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

        // show project discover
        scene.getAccelerators().put(
            new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN),
            ()-> {
                this.controller.showProject();
            }
        );
        // hide project discover
        scene.getAccelerators().put(
            new KeyCodeCombination(KeyCode.ESCAPE),
            ()-> {
                this.controller.hideProject();
            }
        );

        // show db discover
        scene.getAccelerators().put(
            new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN),
            ()-> {
                this.controller.switchDbVisible();
            }
        );

        // show docker discover
        scene.getAccelerators().put(
            new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN),
            ()-> {
                this.controller.switchDockerVisible();
            }
        );

        // show git discover
        scene.getAccelerators().put(
            new KeyCodeCombination(KeyCode.G, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN),
            ()-> {
                this.controller.switchGitVisible();
            }
        );
    }
}
