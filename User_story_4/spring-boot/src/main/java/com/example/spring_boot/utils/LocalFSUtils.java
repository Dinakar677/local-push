package com.example.spring_boot.utils;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class LocalFSUtils {

    public String saveResultSetToFile(String fileName, ResultSet resultSet) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Write headers
            for (int i = 1; i <= columnCount; i++) {
                writer.print(metaData.getColumnName(i) + "\t");
            }
            writer.println();

            // Write rows
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    writer.print(resultSet.getString(i) + "\t");
                }
                writer.println();
            }
            return fileName;
        } catch (Exception e) {
            System.err.println("Error saving data to file: " + e.getMessage());
            return null;
        }
    }
}
