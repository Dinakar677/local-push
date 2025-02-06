package com.example.spring_boot.controllers;

import com.example.spring_boot.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/data")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/report")
    public Map<String, String> generateReport(
            @RequestParam String db_unique_id,
            @RequestParam String schema_name,
            @RequestParam String table_name
    ) {
        Map<String, String> response = new HashMap<>();
        String reportFile = reportService.generateReport(db_unique_id, schema_name, table_name);

        if (reportFile != null) {
            response.put("message", "Report generated successfully");
            response.put("report_file", reportFile);
        } else {
            response.put("message", "Report generation failed");
        }

        return response;
    }
}
