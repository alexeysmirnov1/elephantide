package com.example.demo.database.postgresql.Presentation.UI;

import com.example.demo.ide.UI.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

@org.springframework.stereotype.Component
public class Column extends Component {
    public Column(@Value("classpath:/view/db/parts/column.fxml") Resource resource) {
        this.fxml = resource;
    }
}
