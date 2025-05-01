package com.example.demo.jdbc.postgresql.Presentation.UI;

import com.example.demo.ide.UI.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

@org.springframework.stereotype.Component
public class Schema extends Component {
    public Schema(@Value("classpath:/view/db/parts/schema.fxml") Resource resource) {
        this.fxml = resource;
    }
}
