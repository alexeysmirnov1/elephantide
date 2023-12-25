package com.example.demo.ide.UI.Components.Editor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class Content extends com.example.demo.ide.UI.Component {
    public Content(@Value("classpath:/view/editor/content.fxml") Resource resource) {
        this.fxml = resource;
    }
}
