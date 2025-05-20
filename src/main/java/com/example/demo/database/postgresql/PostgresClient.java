package com.example.demo.database.postgresql;

import java.sql.*;
import java.util.ArrayList;

public class PostgresClient {
    private final String postgresUser;
    private final String postgresPass;
    private final String postgresDBName;

    public PostgresClient(String postgresDBName, String postgresUser, String postgresPass) {
        this.postgresDBName = postgresDBName;
        this.postgresUser = postgresUser;
        this.postgresPass = postgresPass;
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        String url = "jdbc:postgresql://localhost:5454/" + this.postgresDBName;

        Class.forName("org.postgresql.Driver");

        return DriverManager.getConnection(url, this.postgresUser, this.postgresPass);
    }

    public ArrayList<String> getSchemas() throws SQLException, ClassNotFoundException {
        var connection = this.getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT nspname AS schema_name FROM pg_namespace");

        ArrayList<String> schemas = new ArrayList<>();
        while (resultSet.next())
        {
            schemas.add(resultSet.getString("schema_name"));
//            System.out.println(resultSet.getString("schema_name"));
        }

        resultSet.close();
        statement.close();

        return schemas;
    }

    public ArrayList<String> getTablesOfSchema() throws SQLException, ClassNotFoundException
    {
        return this.getTablesOfSchema("public");
    }

    public ArrayList<String> getTablesOfSchema(String schemaName) throws SQLException, ClassNotFoundException {
        var connection = this.getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT table_name\n" +
            "  FROM information_schema.tables\n" +
            " WHERE table_schema='"+schemaName+"'\n" +
            "   AND table_type='BASE TABLE'\n" +
            "ORDER BY table_name ASC;");

        ArrayList<String> tables = new ArrayList<>();
        while (resultSet.next())
        {
            tables.add(resultSet.getString("table_name"));
//            System.out.println(resultSet.getString("table_name"));
        }

        resultSet.close();
        statement.close();

        return tables;
    }

    public ArrayList<String> getColumnsOfTable(String tableName) throws SQLException, ClassNotFoundException {
        return this.getColumnsOfTable("public", tableName);
    }

    public ArrayList<String> getColumnsOfTable(String schemaName, String tableName) throws SQLException, ClassNotFoundException {
        var connection = this.getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT " +
            "column_name, column_default, is_nullable, data_type, character_maximum_length\n" +
            "  FROM information_schema.columns\n" +
            " WHERE table_schema='"+schemaName+"'\n" +
            "   AND table_name='"+tableName+"'\n" +
            "ORDER BY ordinal_position ASC;");

        ArrayList<String> columns = new ArrayList<>();
        while (resultSet.next())
        {
            columns.add(resultSet.getString("column_name"));
//            System.out.println(resultSet.getString("column_name"));
        }

        resultSet.close();
        statement.close();

        return columns;
    }

    public ArrayList<Integer> getFromTable(String tableName) throws SQLException, ClassNotFoundException {
        return this.getFromTable("public", tableName);
    }

    public ArrayList<Integer> getFromTable(String schemaName, String tableName) throws SQLException, ClassNotFoundException {
        var connection = this.getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * " +
            "FROM "+schemaName+"."+tableName + " " +
            "LIMIT 100;");

//        System.out.println("SELECT * " +
//            "FROM "+schemaName+"."+tableName + " " +
//            "LIMIT 100;");

        ArrayList<Integer> rows = new ArrayList<>();
        ArrayList<String> columns = this.getColumnsOfTable(schemaName, tableName);
        while (resultSet.next())
        {
            rows.add(resultSet.getInt("id"));
            String row = "";
            for (String column: columns) {
                row += resultSet.getString(column) + " ";
            }
//            System.out.println(row);
        }

        resultSet.close();
        statement.close();

        return rows;
    }
}
