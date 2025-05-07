package com.example.demo.jdbc.postgresql.Presentation.UI;

import com.example.demo.ide.UI.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

@org.springframework.stereotype.Component
public class DatabaseConnection extends Component {
    public DatabaseConnection(@Value("classpath:/view/db/parts/database.fxml") Resource resource) {
        this.fxml = resource;
    }
}
