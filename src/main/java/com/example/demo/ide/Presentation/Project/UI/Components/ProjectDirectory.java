package com.example.demo.ide.Presentation.Project.UI.Components;

import com.example.demo.ide.UI.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

@org.springframework.stereotype.Component
public class ProjectDirectory extends Component {
    public ProjectDirectory(@Value("classpath:/view/project/components/project-directory.fxml") Resource resource) {
        this.fxml = resource;
    }
}
