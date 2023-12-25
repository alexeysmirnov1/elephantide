package com.example.demo.ide.UI.Components.Editor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class File extends com.example.demo.ide.UI.Component {
    public File(@Value("classpath:/view/editor/file.fxml") Resource resource) {
        this.fxml = resource;
    }
}
