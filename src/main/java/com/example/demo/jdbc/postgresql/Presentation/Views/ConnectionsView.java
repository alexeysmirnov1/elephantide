package com.example.demo.jdbc.postgresql.Presentation.Views;

import com.example.demo.ide.UI.Stage;
import com.example.demo.jdbc.postgresql.PostgresClient;
import com.example.demo.jdbc.postgresql.Presentation.UI.Column;
import com.example.demo.jdbc.postgresql.Presentation.UI.DatabaseConnection;
import com.example.demo.jdbc.postgresql.Presentation.UI.Schema;
import com.example.demo.jdbc.postgresql.Presentation.UI.Table;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;

public abstract class ConnectionsView {
    @Autowired
    protected ConfigurableApplicationContext context;

    @Autowired
    protected Stage stage;

    @FXML
    private VBox databeseConections;

    @FXML
    private Button reloadButton;

    protected PostgresClient postgresClient;

    @FXML
    public void initialize() {
        this.reloadButton.setOnMouseClicked(event -> this.reloadDBs());

        this.reloadDBs();
    }

    protected void reloadDBs() {
        this.databeseConections.getChildren().clear();
        HBox connections = (HBox) this.context.getBean(DatabaseConnection.class).load();
        Label dbName = (Label) connections.lookup("#databaseName");
        dbName.setText("mdat");
        dbName.setOnMouseClicked(event -> this.openCloseDB((VBox) connections.lookup("#schemas")));
        this.databeseConections.getChildren().add(connections);
    }

    protected void openCloseDB(VBox schemasContainer) {
        if(schemasContainer.getChildren().stream().count() > 0) {
            schemasContainer.getChildren().clear();
            return;
        }

        try {
            ArrayList<String> schemas = this.postgresClient.getSchemas();

            for(String schemaName: schemas) {
                HBox schema = (HBox) this.context.getBean(Schema.class).load();
                Label name = (Label) schema.lookup("#schemaName");
                name.setText(schemaName);

                name.setOnMouseClicked(event -> this.openCloseSchema(
                    (VBox) schema.lookup("#tables"),
                    schemaName
                ));

                schemasContainer.getChildren().add(schema);
            }

        } catch (SQLException e) {
            System.out.println("failed loading schemas" + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("failed loading schemas" + e.getMessage());
        }
    }

    protected void openCloseSchema(VBox tablesContainer, String schemaName) {
        if(tablesContainer.getChildren().stream().count() > 0) {
            tablesContainer.getChildren().clear();
            return;
        }

        try {
            ArrayList<String> tables = this.postgresClient.getTablesOfSchema(schemaName);

            for(String tableName: tables) {
                HBox table = (HBox) this.context.getBean(Table.class).load();
                Label name = (Label) table.lookup("#tableName");
                name.setText(tableName);

                name.setOnMouseClicked(event -> this.openCloseTable(
                        (VBox) table.lookup("#columns"),
                        schemaName,
                        tableName
                ));

                tablesContainer.getChildren().add(table);
            }

        } catch (SQLException e) {
            System.out.println("failed loading tables");
        } catch (ClassNotFoundException e) {
            System.out.println("failed loading tables");
        }
    }

    protected void openCloseTable(VBox columnsContainer, String schemaName, String tableName) {
        if(columnsContainer.getChildren().stream().count() > 0) {
            columnsContainer.getChildren().clear();
            return;
        }

        try {
            ArrayList<String> columns = this.postgresClient.getColumnsOfTable(schemaName, tableName);

            for(String columnName: columns) {
                HBox column = (HBox) this.context.getBean(Column.class).load();
                Label name = (Label) column.lookup("#columnName");
                name.setText(columnName);

                columnsContainer.getChildren().add(column);
            }

        } catch (SQLException e) {
            System.out.println("failed loading tables");
        } catch (ClassNotFoundException e) {
            System.out.println("failed loading tables");
        }
    }
}
