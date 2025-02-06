package com.example.spring_boot.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class LocalFSUtils {

    public String saveResultSetToJSON(String fileName, ResultSet resultSet) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            ObjectMapper objectMapper = new ObjectMapper();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Write rows as JSON objects
            while (resultSet.next()) {
                ObjectNode rowObject = objectMapper.createObjectNode();
                for (int i = 1; i <= columnCount; i++) {
                    rowObject.put(metaData.getColumnName(i), resultSet.getString(i));
                }
                fileWriter.write(rowObject.toString() + System.lineSeparator());
            }

            return fileName;
        } catch (Exception e) {
            System.err.println("Error saving data to JSON file: " + e.getMessage());
            return null;
        }
    }
}
