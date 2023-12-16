package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.DTO.CustomerDTO;
import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper; // Example: Using ModelMapper for mapping

    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));
        return convertToDTO(customer);
    }

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = convertToEntity(customerDTO);
        // Perform validations or additional logic before saving
        Customer savedCustomer = customerRepository.save(customer);
        return convertToDTO(savedCustomer);
    }

    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();

            // Update existing customer fields
            existingCustomer.setFirstName(customerDTO.getFirstName());
            existingCustomer.setLastName(customerDTO.getLastName());
            existingCustomer.setEmail(customerDTO.getEmail());
            // Update other fields as needed

            // Save the updated customer
            Customer updatedCustomer = customerRepository.save(existingCustomer);
            return modelMapper.map(updatedCustomer, CustomerDTO.class);
        } else {
            // Handle the case when the customer with the provided ID is not found
            return null;
        }
    }

    public boolean deleteCustomer(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customerToDelete = optionalCustomer.get();
            customerRepository.delete(customerToDelete);
            return true;
        } else {
            // Handle the case when the customer with the provided ID is not found
            return false;
        }
    }

    private CustomerDTO convertToDTO(Customer customer) {
        return modelMapper.map(customer, CustomerDTO.class);
    }

    private Customer convertToEntity(CustomerDTO customerDTO) {
        return modelMapper.map(customerDTO, Customer.class);
    }
}
