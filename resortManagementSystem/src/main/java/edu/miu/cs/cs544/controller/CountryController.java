package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.DTO.CountryDTO;
import edu.miu.cs.cs544.service.CountryService;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/{id}")
    public ResponseEntity<CountryDTO> getCountryById(@PathVariable Long id) {
        CountryDTO countryDTO = countryService.getCountryById(id);
        return ResponseEntity.ok(countryDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<CountryDTO> createCountry(@RequestBody CountryDTO countryDTO) {
        CountryDTO createdCountry = countryService.createCountry(countryDTO);
        return new ResponseEntity<>(createdCountry, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CountryDTO> updateCountry(@PathVariable Long id, @RequestBody CountryDTO countryDTO) {
        CountryDTO updatedCountry = countryService.updateCountry(id, countryDTO);
        if (updatedCountry != null) {
            return ResponseEntity.ok(updatedCountry);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCountry(@PathVariable Long id) {
        boolean isDeleted = countryService.deleteCountry(id);
        if (isDeleted) {
            return ResponseEntity.ok("Country with ID: " + id + " has been deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Country with ID: " + id + " not found.");
        }
    }

}