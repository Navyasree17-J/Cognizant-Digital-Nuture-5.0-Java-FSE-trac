package com.cognizant.ormlearn.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.exception.CountryNotFoundException;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    // Hands-on 1 & 5: Get all countries
    @Transactional(readOnly = true)
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    // Hands-on 6: Find a country by code
    @Transactional(readOnly = true)
    public Country findCountryByCode(String countryCode) throws CountryNotFoundException {
        Optional<Country> result = countryRepository.findById(countryCode);
        if (!result.isPresent()) {
            throw new CountryNotFoundException("Country not found");
        }
        return result.get();
    }

    // Hands-on 7: Add a new country
    @Transactional
    public void addCountry(Country country) {
        countryRepository.save(country);
    }

    // Hands-on 8: Update a country name
    @Transactional
    public void updateCountry(String code, String name) throws CountryNotFoundException {
        Optional<Country> result = countryRepository.findById(code);
        if (!result.isPresent()) {
            throw new CountryNotFoundException("Country not found");
        }
        Country country = result.get();
        country.setName(name);
        countryRepository.save(country);
    }

    // Hands-on 9: Delete a country
    @Transactional
    public void deleteCountry(String code) {
        countryRepository.deleteById(code);
    }
}