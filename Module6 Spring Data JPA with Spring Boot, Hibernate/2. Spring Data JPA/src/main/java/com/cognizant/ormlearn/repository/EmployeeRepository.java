package com.cognizant.ormlearn.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cognizant.ormlearn.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // Hands-on 2: Fetch all permanent employees optimized with 'fetch' to populate linked graphs in 1 query
    @Query(value = "SELECT e FROM Employee e LEFT JOIN FETCH e.department d LEFT JOIN FETCH e.skillList WHERE e.permanent = true")
    List<Employee> getAllPermanentEmployees();

    // Hands-on 4: Compute the average salary of a specific department using HQL aggregate function & parameter binding
    @Query(value = "SELECT AVG(e.salary) FROM Employee e WHERE e.department.id = :id")
    Double getAverageSalary(@Param("id") int id);

    // Hands-on 5: Retrieve all records directly using Native SQL query bypass
    @Query(value = "SELECT * FROM employee", nativeQuery = true)
    List<Employee> getAllEmployeesNative();
}