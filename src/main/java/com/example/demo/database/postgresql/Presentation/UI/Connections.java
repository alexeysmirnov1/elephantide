package com.example.demo.database.postgresql.Presentation.UI;

import com.example.demo.ide.UI.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

@org.springframework.stereotype.Component
public class Connections extends Component {
    public Connections(@Value("classpath:/view/db/connections.fxml") Resource resource) {
        this.fxml = resource;
    }
}
