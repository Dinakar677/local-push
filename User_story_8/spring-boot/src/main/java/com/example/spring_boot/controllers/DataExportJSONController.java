package com.example.spring_boot.controllers;

import com.example.spring_boot.services.DataExportJSONService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/data")
public class DataExportJSONController {

    @Autowired
    private DataExportJSONService dataExportJSONService;

    @GetMapping("/export/json")
    public Map<String, String> exportDataToJSON(
            @RequestParam String db_unique_id,
            @RequestParam String schema_name,
            @RequestParam String table_name
    ) {
        Map<String, String> response = new HashMap<>();
        String filePath = dataExportJSONService.exportTableToJSON(db_unique_id, schema_name, table_name);

        if (filePath != null) {
            response.put("message", "Data exported to JSON successfully");
            response.put("file_location", filePath);
        } else {
            response.put("message", "Data export failed");
        }

        return response;
    }
}
