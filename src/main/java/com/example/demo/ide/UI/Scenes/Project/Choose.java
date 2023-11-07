package com.example.demo.ide.UI.Scenes.Project;

import com.example.demo.ide.UI.Scene;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class Choose extends Scene {
    public Choose(@Value("classpath:/view/project/choose-type.fxml") Resource resource) {
        this.fxml = resource;
        this.width = 1000;
        this.height = 700;
    }
}
