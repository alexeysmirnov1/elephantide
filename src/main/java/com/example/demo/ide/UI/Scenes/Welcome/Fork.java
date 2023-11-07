package com.example.demo.ide.UI.Scenes.Welcome;

import com.example.demo.ide.UI.Scene;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class Fork extends Scene {
    public Fork(@Value("classpath:/view/welcome/fork.fxml") Resource resource) {
        this.fxml = resource;
        this.width = 1000;
        this.height = 700;
    }
}
