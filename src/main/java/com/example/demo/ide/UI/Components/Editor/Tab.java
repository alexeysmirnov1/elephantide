package com.example.demo.ide.UI.Components.Editor;

import com.example.demo.ide.UI.Scene;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class Tab extends com.example.demo.ide.UI.Component {
    public Tab(@Value("classpath:/view/editor/tab.fxml") Resource resource) {
        this.fxml = resource;
    }

//    public void afterCreating(javafx.scene.Scene scene) {
//        scene.getStylesheets().add(
//            Tab.class.getClassLoader().getResource("css/phptokens.css").toExternalForm()
//        );
//    }
}
