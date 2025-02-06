package com.example.spring_boot.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class FileUtils {

    public Map<String, String> getConnectionDetails(String dbUniqueId, String filePath) {
        Map<String, String> connectionDetails = new HashMap<>();
        File file = new File(filePath);

        if (!file.exists()) {
            throw new RuntimeException("Connection file not found: " + filePath);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("dbUniqueId: " + dbUniqueId)) {
                    connectionDetails.put("dbUniqueId", dbUniqueId);
                } else if (line.startsWith("url:")) {
                    connectionDetails.put("url", line.split("url:")[1].trim());
                } else if (line.startsWith("username:")) {
                    connectionDetails.put("username", line.split("username:")[1].trim());
                } else if (line.startsWith("password:")) {
                    connectionDetails.put("password", line.split("password:")[1].trim());
                } else if (line.startsWith("port:")) {
                    connectionDetails.put("port", line.split("port:")[1].trim());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error reading connection file: " + e.getMessage());
        }

        if (connectionDetails.isEmpty() || !connectionDetails.containsKey("url")) {
            throw new RuntimeException("Invalid or missing connection details in file.");
        }

        return connectionDetails;
    }
}
