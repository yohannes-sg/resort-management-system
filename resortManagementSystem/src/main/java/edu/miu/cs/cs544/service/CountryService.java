package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.DTO.CountryDTO;
import edu.miu.cs.cs544.domain.Country;
import edu.miu.cs.cs544.repository.CountryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CountryService {
    @Autowired
    private  CountryRepository countryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CountryDTO getCountryById(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Country not found"));
        return convertToDTO(country);
    }

    public CountryDTO createCountry(CountryDTO countryDTO) {
        Country country = convertToEntity(countryDTO);
        // Perform validations or additional logic before saving
        Country savedCountry = countryRepository.save(country);
        return convertToDTO(savedCountry);
    }

    public CountryDTO updateCountry(Long id, CountryDTO countryDTO) {
        Optional<Country> optionalCountry = countryRepository.findById(id);
        if (optionalCountry.isPresent()) {
            Country existingCountry = optionalCountry.get();

            // Update existing country fields
            existingCountry.setCountryName(countryDTO.getCountryName());
            // Update other fields as needed

            // Save the updated country
            Country updatedCountry = countryRepository.save(existingCountry);
            return modelMapper.map(updatedCountry, CountryDTO.class);
        } else {
            // Handle the case when the country with the provided ID is not found
            return null;
        }
    }

    public boolean deleteCountry(Long id) {
        Optional<Country> optionalCountry = countryRepository.findById(id);
        if (optionalCountry.isPresent()) {
            Country countryToDelete = optionalCountry.get();
            countryRepository.delete(countryToDelete);
            return true;
        } else {
            // Handle the case when the country with the provided ID is not found
            return false;
        }
    }

    private CountryDTO convertToDTO(Country country) {
        return modelMapper.map(country, CountryDTO.class);
    }

    private Country convertToEntity(CountryDTO countryDTO) {
        return modelMapper.map(countryDTO, Country.class);
    }
}
