package com.example.demo.docker.Presentation.UI;

import com.example.demo.ide.UI.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

@org.springframework.stereotype.Component
public class DockerServicesTable extends Component {
    public DockerServicesTable(@Value("classpath:/view/docker/services.fxml") Resource resource) {
        this.fxml = resource;
    }
}
