package com.example.demo.ide.Presentation.Editor.UI.Components;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class File extends com.example.demo.ide.UI.Component {
    public File(@Value("classpath:/view/editor/file.fxml") Resource resource) {
        this.fxml = resource;
    }

    public void setName(String name) {
        Label label = (Label) this.component.lookup("#fileName");
        label.setText(name);
    }

    public void addEventHandler(EventHandler<? super MouseEvent> value) {
        this.component.setOnMouseClicked(value);
    }
}
