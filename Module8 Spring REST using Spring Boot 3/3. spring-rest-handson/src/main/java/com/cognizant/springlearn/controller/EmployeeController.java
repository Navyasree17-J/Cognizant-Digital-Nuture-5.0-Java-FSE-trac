package com.cognizant.springlearn.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cognizant.springlearn.model.Employee;
import com.cognizant.springlearn.model.Department;
import com.cognizant.springlearn.repository.EmployeeRepository;
import com.cognizant.springlearn.repository.DepartmentRepository;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private DepartmentRepository departmentRepository;

    // Fetch details in human-readable plain text format instead of JSON format
    @GetMapping(value = "/employees", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getAllEmployeesNormalForm() {
        List<Employee> list = employeeRepository.findAll();
        
        if (list == null || list.isEmpty()) {
            return "No employee records found in the database.";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-5s | %-20s | %-12s | %-10s | %-15s\n", "ID", "NAME", "SALARY", "PERMANENT", "DEPARTMENT"));
        sb.append("--------------------------------------------------------------------------------\n");
        for (Employee emp : list) {
            String deptName = (emp.getDepartment() != null) ? emp.getDepartment().getName() : "N/A";
            sb.append(String.format("%-5d | %-20s | %-12.2f | %-10b | %-15s\n", 
                    emp.getId(), emp.getName(), emp.getSalary(), emp.isPermanent(), deptName));
        }
        return sb.toString();
    }

    // Endpoint to capture and persist new employee entries
    @PostMapping(value = "/employees", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> addNewEmployee(@RequestBody Employee employee) {
        if (employee.getDepartment() != null) {
            // FIXED: Changed departmentRepository.findAll() to findById() so orElse(null) compiles perfectly!
            Department dept = departmentRepository.findById(employee.getDepartment().getId()).orElse(null);
            employee.setDepartment(dept);
        }
        
        Employee saved = employeeRepository.save(employee);
        return ResponseEntity.ok("SUCCESS: Saved Employee '" + saved.getName() + "' with Generated ID: " + saved.getId());
    }
}