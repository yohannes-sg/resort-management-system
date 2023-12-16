package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.DTO.AddressDTO;
import edu.miu.cs.cs544.domain.Address;
import edu.miu.cs.cs544.repository.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    public AddressDTO getAddressById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Address not found"));
        return convertToDTO(address);
    }

    public AddressDTO createAddress(AddressDTO addressDTO) {
        Address address = convertToEntity(addressDTO);
        // Perform validations or additional logic before saving
        Address savedAddress = addressRepository.save(address);
        return convertToDTO(savedAddress);
    }

    public AddressDTO updateAddress(Long id, AddressDTO addressDTO) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            Address existingAddress = optionalAddress.get();

            // Update existing address fields
            existingAddress.setStreet(addressDTO.getStreet());
            existingAddress.setCity(addressDTO.getCity());
            existingAddress.setPostalCode(addressDTO.getPostalCode());
            // Update other fields as needed

            // Save the updated address
            Address updatedAddress = addressRepository.save(existingAddress);
            return modelMapper.map(updatedAddress, AddressDTO.class);
        } else {
            // Handle the case when the address with the provided ID is not found
            return null;
        }
    }

    public boolean deleteAddress(Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            Address addressToDelete = optionalAddress.get();
            addressRepository.delete(addressToDelete);
            return true;
        } else {
            // Handle the case when the address with the provided ID is not found
            return false;
        }
    }

    private AddressDTO convertToDTO(Address address) {
        return modelMapper.map(address, AddressDTO.class);
    }

    private Address convertToEntity(AddressDTO addressDTO) {
        return modelMapper.map(addressDTO, Address.class);
    }
}