package com.cognizant.springlearn.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DateController {

    @GetMapping("/api/date/parse")
    public ResponseEntity<Map<String, String>> parseDate(@RequestParam String dateStr) {
        Map<String, String> response = new HashMap<>();
        try {
            // Load spring configuration file from application context
            @SuppressWarnings("resource")
			ApplicationContext context = new ClassPathXmlApplicationContext("date-format.xml");
            
            // Retrieve bean instance using getBean()
            SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);
            
            // Parse the incoming request string parameter
            Date parsedDate = format.parse(dateStr);
            
            response.put("status", "SUCCESS");
            response.put("message", "Date parsed successfully using configured XML Bean!");
            response.put("result", parsedDate.toString());
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", "Parsing Failure: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}