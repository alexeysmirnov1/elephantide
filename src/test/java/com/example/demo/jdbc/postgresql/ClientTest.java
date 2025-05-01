package com.example.demo.jdbc.postgresql;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ClientTest {
    @Test
    public void testConnection() throws SQLException, ClassNotFoundException {
        var client = this.getClient();
        Connection connection = client.getConnection();

        assertTrue(connection.isValid(10));
        assertFalse(connection.isClosed());
    }

    @Test
    public void testSchemas() throws SQLException, ClassNotFoundException {
        var client = this.getClient();

        assertFalse(client.getSchemas().isEmpty());
    }

    @Test
    public void testTables() throws SQLException, ClassNotFoundException {
        var client = this.getClient();

        assertFalse(client.getTablesOfSchema().isEmpty());
    }

    @Test
    public void testColumns() throws SQLException, ClassNotFoundException {
        var client = this.getClient();

        assertFalse(client.getColumnsOfTable("users").isEmpty());
    }

    @Test
    public void testTableContent() throws SQLException, ClassNotFoundException {
        var client = this.getClient();

        assertFalse(client.getFromTable("users").isEmpty());
    }

    private PostgresClient getClient() {
        return new PostgresClient(
            "mdat",
            "mdat",
            "mdat123"
        );
    }
}
