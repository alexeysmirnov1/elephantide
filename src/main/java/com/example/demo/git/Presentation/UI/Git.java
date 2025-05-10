package com.example.demo.git.Presentation.UI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class Git extends com.example.demo.ide.UI.Component {
    public Git(@Value("classpath:/view/git/index.fxml") Resource resource) {
        this.fxml = resource;
    }
}
