package com.example.demo.services;

import com.example.demo.Exceptions.DoesNotExist;
import com.example.demo.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service

public class ProductService {

    private long ID = 0;
    private List<Product> products = new ArrayList<>();


    {
        products.add(new Product(++ID, "title", "description", "Moscow", "Sobyanin", 120.2));
        products.add(new Product(++ID, "Bible", "About Love of God", "Everywhere", "Jesus Christ is a main character", 999.9));
    }



    public List<Product> getAll() {
        return products;
    }

    public void saveProduct(Product product) {
        product.setId(++ID);
        products.add(product);
    }

    public Product getById(int id) {
        try {
            var product = products.get(id);
            return  product;
        }
        catch (IndexOutOfBoundsException e) {
            return null;
        }

    }

    public void addProduct(Product product) {
        product.setId(++ID);
        products.add(product);
    }

    public void deleteProduct(Long id) {
        try {
            products.removeIf(product -> product.getId().equals(id));
        } catch (Exception e) {
            throw new IndexOutOfBoundsException();
        }
    }
}
