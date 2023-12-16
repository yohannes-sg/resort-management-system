package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.DTO.AdminDTO;
import edu.miu.cs.cs544.DTO.CustomerDTO;
import edu.miu.cs.cs544.DTO.UserDTO;
import edu.miu.cs.cs544.domain.Admin;
import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper; // Example: Using ModelMapper for mapping

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        return convertToDTO(user);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        // Perform validations or additional logic before saving
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            // Update existing user fields
            existingUser.setUserName(userDTO.getUserName());
            existingUser.setUserPass(userDTO.getUserPass());
            // Update other fields as needed

            // Save the updated user
            User updatedUser = userRepository.save(existingUser);
            return modelMapper.map(updatedUser, UserDTO.class);
        } else {
            // Handle the case when the user with the provided ID is not found
            return null;
        }
    }

    public boolean deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User userToDelete = optionalUser.get();
            userRepository.delete(userToDelete);
            return true;
        } else {
            // Handle the case when the user with the provided ID is not found
            return false;
        }
    }

    private UserDTO convertToDTO(User user) {
        if (user instanceof Admin) {
            return modelMapper.map(user, AdminDTO.class);
        } else if (user instanceof Customer) {
            return modelMapper.map(user, CustomerDTO.class);
        }
        // Handle other user types if needed
        return null;
    }

    private User convertToEntity(UserDTO userDTO) {
        if (userDTO instanceof AdminDTO) {
            return modelMapper.map(userDTO, Admin.class);
        } else if (userDTO instanceof CustomerDTO) {
            return modelMapper.map(userDTO, Customer.class);
        }
        // Handle other user types if needed
        return null;
    }
}
