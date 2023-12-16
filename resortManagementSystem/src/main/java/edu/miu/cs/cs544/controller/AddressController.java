package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.DTO.AddressDTO;
import edu.miu.cs.cs544.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long id) {
        AddressDTO addressDTO = addressService.getAddressById(id);
        return ResponseEntity.ok(addressDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO addressDTO) {
        AddressDTO createdAddress = addressService.createAddress(addressDTO);
        return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTO) {
        AddressDTO updatedAddress = addressService.updateAddress(id, addressDTO);
        if (updatedAddress != null) {
            return ResponseEntity.ok(updatedAddress);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long id) {
        boolean isDeleted = addressService.deleteAddress(id);
        if (isDeleted) {
            return ResponseEntity.ok("Address with ID: " + id + " has been deleted.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // Other endpoints for AddressController...
}