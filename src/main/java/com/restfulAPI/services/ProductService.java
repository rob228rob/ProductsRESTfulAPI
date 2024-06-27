package com.restfulAPI.services;

import com. restfulAPI.Repository.ProductRepository;
import com.restfulAPI.Repository.ProductRepository;
import com.restfulAPI.dto.ProductDTO;
import com.restfulAPI.models.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

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

    public Product updateProduct(Product product) {
//        Product product = Product.builder()
//                .name(dto.getProductName())
//                .price(dto.getProductPrice())
//                .build();
        productRepository.deleteById(product.getId());
        return productRepository.save(product);
        //return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}