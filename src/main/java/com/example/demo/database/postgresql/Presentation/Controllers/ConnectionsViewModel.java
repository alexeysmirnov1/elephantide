package com.example.demo.database.postgresql.Presentation.Controllers;

import com.example.demo.database.postgresql.PostgresClient;
import com.example.demo.database.postgresql.Presentation.Views.ConnectionsView;
import javafx.fxml.FXML;
import org.springframework.stereotype.Component;

@Component
public class ConnectionsViewModel extends ConnectionsView {
    @FXML
    public void initialize() {
        super.initialize();

        this.postgresClient = new PostgresClient(
                "mdat",
                "mdat",
                "mdat123"
        );
    }
}
