package com.cognizant.springlearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cognizant.springlearn.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    // Keep this completely empty! 
    // JpaRepository automatically creates the findById() returning an Optional box.
}