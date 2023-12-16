package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.DTO.ProductDTO;
import edu.miu.cs.cs544.domain.Product;
import edu.miu.cs.cs544.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper; // Example: Using ModelMapper for mapping

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));
        return convertToDTO(product);
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        // Perform validations or additional logic before saving
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();

            // Update existing product fields
            existingProduct.setName(productDTO.getName());
            existingProduct.setDescription(productDTO.getDescription());
            existingProduct.setNightlyRate(productDTO.getNightlyRate());
            existingProduct.setMaxCapacity(productDTO.getMaxCapacity());
            // Update other fields as needed

            // Save the updated product
            Product updatedProduct = productRepository.save(existingProduct);
            return modelMapper.map(updatedProduct, ProductDTO.class);
        } else {
            // Handle the case when the product with the provided ID is not found
            return null;
        }
    }

    public boolean deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product productToDelete = optionalProduct.get();
            productRepository.delete(productToDelete);
            return true;
        } else {
            // Handle the case when the product with the provided ID is not found
            return false;
        }
    }

    private ProductDTO convertToDTO(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    private Product convertToEntity(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }
}