package com.example.spring_boot.models;

public class ExportJSONRequest {
    private String dbUniqueId;
    private String schemaName;
    private String tableName;

    // Getters and Setters
    public String getDbUniqueId() { return dbUniqueId; }
    public void setDbUniqueId(String dbUniqueId) { this.dbUniqueId = dbUniqueId; }
    public String getSchemaName() { return schemaName; }
    public void setSchemaName(String schemaName) { this.schemaName = schemaName; }
    public String getTableName() { return tableName; }
    public void setTableName(String tableName) { this.tableName = tableName; }
}
