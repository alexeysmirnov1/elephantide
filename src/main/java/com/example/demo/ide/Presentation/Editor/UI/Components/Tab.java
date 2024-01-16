package com.example.demo.ide.Presentation.Editor.UI.Components;

import com.example.demo.ide.UI.Scene;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class Tab extends com.example.demo.ide.UI.Component {
    public Tab(@Value("classpath:/view/editor/tab.fxml") Resource resource) {
        this.fxml = resource;
    }

    public void setTitle(String title) {
        Label property = (Label) this.component.lookup("#title");
        property.setText(title);
    }

    public void addEventHandler(EventHandler<? super MouseEvent> value) {
        this.component.setOnMouseClicked(value);
    }
}
