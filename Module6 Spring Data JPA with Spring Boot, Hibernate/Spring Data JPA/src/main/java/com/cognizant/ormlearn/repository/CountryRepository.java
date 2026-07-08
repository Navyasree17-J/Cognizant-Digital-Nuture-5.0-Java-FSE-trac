package com.cognizant.ormlearn.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cognizant.ormlearn.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    // Task 1 & 2: Search by containing text and sort by name ascending automatically
    List<Country> findByNameContainingOrderByNameAsc(String namePart);

    // Task 3: Filter countries whose names start with a specific character/string
    List<Country> findByNameStartingWithOrderByNameAsc(String startLetter);
}