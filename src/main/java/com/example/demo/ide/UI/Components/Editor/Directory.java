package com.example.demo.ide.UI.Components.Editor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class Directory extends com.example.demo.ide.UI.Component {
    public Directory(@Value("classpath:/view/editor/directory.fxml") Resource resource) {
        this.fxml = resource;
    }
}
