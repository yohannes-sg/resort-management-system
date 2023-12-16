package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.DTO.AdminDTO;
import edu.miu.cs.cs544.domain.Admin;
import edu.miu.cs.cs544.repository.AdminRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ModelMapper modelMapper;

    public AdminDTO getAdminById(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Admin not found"));
        return convertToDTO(admin);
    }

    public AdminDTO createAdmin(AdminDTO adminDTO) {
        Admin admin = convertToEntity(adminDTO);
        // Perform validations or additional logic before saving
        Admin savedAdmin = adminRepository.save(admin);
        return convertToDTO(savedAdmin);
    }

    public AdminDTO updateAdmin(Long id, AdminDTO adminDTO) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        if (optionalAdmin.isPresent()) {
            Admin existingAdmin = optionalAdmin.get();

            // Update existing admin fields
            existingAdmin.setFirstName(adminDTO.getFirstName());
            existingAdmin.setLastName(adminDTO.getLastName());
            existingAdmin.setEmail(adminDTO.getEmail());
            // Update other fields as needed

            // Save the updated admin
            Admin updatedAdmin = adminRepository.save(existingAdmin);
            return modelMapper.map(updatedAdmin, AdminDTO.class);
        } else {
            // Handle the case when the admin with the provided ID is not found
            return null;
        }
    }

    public boolean deleteAdmin(Long id) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        if (optionalAdmin.isPresent()) {
            Admin adminToDelete = optionalAdmin.get();
            adminRepository.delete(adminToDelete);
            return true;
        } else {
            // Handle the case when the admin with the provided ID is not found
            return false;
        }
    }

    private AdminDTO convertToDTO(Admin admin) {
        return modelMapper.map(admin, AdminDTO.class);
    }

    private Admin convertToEntity(AdminDTO adminDTO) {
        return modelMapper.map(adminDTO, Admin.class);
    }
}
