package com.cognizant.springlearn.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cognizant.springlearn.model.Department;
import com.cognizant.springlearn.repository.DepartmentRepository;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    // Final Hands-on Endpoint: Fetch all departments in a neat tabular/normal format
    @GetMapping(value = "/departments", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getAllDepartmentsNormalForm() {
        List<Department> list = departmentRepository.findAll();
        
        if (list == null || list.isEmpty()) {
            return "No department records found in the database.";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-10s | %-30s\n", "DEPT ID", "DEPARTMENT NAME"));
        sb.append("--------------------------------------------------\n");
        for (Department dept : list) {
            sb.append(String.format("%-10d | %-30s\n", dept.getId(), dept.getName()));
        }
        return sb.toString();
    }
}