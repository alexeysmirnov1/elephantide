package com.example.demo.ide.UI.Scenes.Welcome;

import com.example.demo.ide.UI.Scene;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class Load extends Scene {
    public Load(@Value("classpath:/view/welcome/load.fxml") Resource resource) {
        this.fxml = resource;
        this.width = 1000;
        this.height = 700;
    }
}
