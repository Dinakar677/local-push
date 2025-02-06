package com.example.spring_boot.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataExportJSONDAO {

    public ResultSet fetchTableData(Connection connection, String schemaName, String tableName) {
        try {
            String query = String.format("SELECT * FROM %s.%s", schemaName, tableName);
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (Exception e) {
            System.err.println("Error fetching data from table: " + e.getMessage());
            return null;
        }
    }
}
