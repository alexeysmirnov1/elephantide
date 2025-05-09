package com.example.demo.ide.UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.net.URL;

public class Scene {
    @Autowired
    protected ConfigurableApplicationContext context;

    protected Resource fxml;

    protected double width = 900.0;
    protected double height = 400.0;

    public javafx.scene.Scene load() {
        try {
            URL url = this.fxml.getURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            fxmlLoader.setControllerFactory(this.context::getBean);
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

    }
}
