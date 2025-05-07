package com.example.demo.ide.Presentation.Editor.Views;

import com.example.demo.ide.UI.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

public abstract class DatabaseView {
    @Autowired
    protected ConfigurableApplicationContext context;

    @Autowired
    protected Stage stage;

    @FXML
    protected TabPane tabPane;

    @FXML
    protected HBox databases;

    @FXML
    public void initialize() {

    }
}
