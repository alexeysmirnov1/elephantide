package com.example.demo.ide.UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.net.URL;

public class Component {
    @Autowired
    private ConfigurableApplicationContext context;

    protected Resource fxml;

    public Parent load() {
        try {
            URL url = this.fxml.getURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            fxmlLoader.setControllerFactory(this.context::getBean);
            return fxmlLoader.load();
        } catch (
            IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void afterCreating(javafx.scene.Scene scene) {

    }
}
