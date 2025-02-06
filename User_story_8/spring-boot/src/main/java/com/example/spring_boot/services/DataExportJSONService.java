package com.example.spring_boot.services;

import com.example.spring_boot.dao.DataExportJSONDAO;
import com.example.spring_boot.utils.FileUtils;
import com.example.spring_boot.utils.JDBCUtils;
import com.example.spring_boot.utils.LocalFSUtils;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;

@Service
public class DataExportJSONService {

    private final JDBCUtils jdbcUtils = new JDBCUtils();
    private final FileUtils fileUtils = new FileUtils();
    private final LocalFSUtils localFSUtils = new LocalFSUtils();
    private final DataExportJSONDAO dataExportJSONDAO = new DataExportJSONDAO();
    private static final String CONNECTION_FILE_PATH = "/home/mt24048/Documents/User_story_1/user-story-1/";

    public String exportTableToJSON(String dbUniqueId, String schemaName, String tableName) {
        Connection connection = null;
        String filePath = null;

        try {
            // Construct connection file path
            String connectionFile = CONNECTION_FILE_PATH + dbUniqueId + ".txt";

            // Establish database connection
            connection = jdbcUtils.getConnection(dbUniqueId, connectionFile);

            if (connection != null) {
                ResultSet resultSet = dataExportJSONDAO.fetchTableData(connection, schemaName, tableName);
                filePath = localFSUtils.saveResultSetToJSON(
                        "/home/mt24048/Documents/User_story_8/spring-boot/" + tableName + ".json", resultSet);
            }
        } catch (Exception e) {
            System.err.println("Error exporting data to JSON: " + e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (Exception ex) {
                System.err.println("Error closing connection: " + ex.getMessage());
            }
        }

        return filePath;
    }
}
