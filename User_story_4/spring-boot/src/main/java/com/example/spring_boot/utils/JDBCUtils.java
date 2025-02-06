package com.example.spring_boot.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class JDBCUtils {

    private final FileUtils fileUtils = new FileUtils();

    public Connection getConnection(String dbUniqueId, String filePath) {
        try {
            // Retrieve connection details from the file
            Map<String, String> connectionDetails = fileUtils.getConnectionDetails(dbUniqueId, filePath);

            // Extract connection parameters
            String url = connectionDetails.get("url");
            String username = connectionDetails.get("username");
            String password = connectionDetails.get("password");

            // Establish and return the connection
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.err.println("Failed to create a connection: " + e.getMessage());
            return null;
        }
    }
}
