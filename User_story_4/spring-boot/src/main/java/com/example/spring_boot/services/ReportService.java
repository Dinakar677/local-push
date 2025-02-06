package com.example.spring_boot.services;

import com.example.spring_boot.dao.ReportDAO;
import com.example.spring_boot.utils.JDBCUtils;
import com.example.spring_boot.utils.LocalFSUtils;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;

@Service
public class ReportService {

    private final JDBCUtils jdbcUtils = new JDBCUtils();
    private final LocalFSUtils localFSUtils = new LocalFSUtils();
    private final ReportDAO reportDAO = new ReportDAO();
    private static final String CONNECTION_FILE_PATH = "/home/mt24048/Documents/User_story_1/user-story-1/";

    public String generateReport(String dbUniqueId, String schemaName, String tableName) {
        Connection connection = null;
        String reportFilePath = null;

        try {
            // Construct the connection file path
            String connectionFile = CONNECTION_FILE_PATH + dbUniqueId + ".txt";

            // Establish the database connection
            connection = jdbcUtils.getConnection(dbUniqueId, connectionFile);

            if (connection != null) {
                ResultSet resultSet = reportDAO.fetchAggregatedData(connection, schemaName, tableName);

                reportFilePath = localFSUtils.saveResultSetToFile(
                        "/home/mt24048/Documents/User_story_4/spring-boot/" + tableName + "_report.txt",
                        resultSet
                );
            }
        } catch (Exception e) {
            System.err.println("Error generating report: " + e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (Exception ex) {
                System.err.println("Error closing connection: " + ex.getMessage());
            }
        }

        return reportFilePath;
    }
}
