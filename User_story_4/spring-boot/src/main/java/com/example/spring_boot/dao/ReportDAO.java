package com.example.spring_boot.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReportDAO {

    public ResultSet fetchAggregatedData(Connection connection, String schemaName, String tableName) {
        try {
            String query = String.format("SELECT program_name, COUNT(*) AS count FROM %s.%s GROUP BY program_name", schemaName, tableName);
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (Exception e) {
            System.err.println("Error fetching aggregated data: " + e.getMessage());
            return null;
        }
    }

}
