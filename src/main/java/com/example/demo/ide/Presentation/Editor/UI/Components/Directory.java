package com.example.demo.ide.Presentation.Editor.UI.Components;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class Directory extends com.example.demo.ide.UI.Component {
    public Directory(@Value("classpath:/view/editor/directory.fxml") Resource resource) {
        this.fxml = resource;
    }

    public void setName(String name) {
        Label label = (Label) this.component.lookup("#dirName");
        label.setText(name);
    }

    public void addEventHandler(EventHandler<? super MouseEvent> value) {
        this.component.setOnMouseClicked(value);
    }

    public void clearContent() {
        VBox children = (VBox) this.component.lookup("#children");
        if(children.getChildren().stream().count() > 0) {
            children.getChildren().clear();
        }
    }

    public void addContent(com.example.demo.ide.UI.Component child) {
        VBox children = (VBox) this.component.lookup("#children");
        children.getChildren().add(child.fxml());
    }
}
