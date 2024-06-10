package com.example.demo.services;

import com.example.demo.Repository.ProductRepository;
import com.example.demo.dto.ProductDTO;
import com.example.demo.models.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product createProduct(ProductDTO dto) {
        Product product = Product.builder()
                .name(dto.getProductName())
                .price(dto.getProductPrice())
                .build();

        return productRepository.save(product);
    }

    public List<Product> readAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> readProduct(Long id) {
        var product = productRepository.findById(id);
        if (product.isPresent()) {
            return Optional.of(product.get());
        }

        return Optional.empty();
    }

    public Product updateProduct(ProductDTO dto) {
        Product product = Product.builder()
                .name(dto.getProductName())
                .price(dto.getProductPrice())
                .build();

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}