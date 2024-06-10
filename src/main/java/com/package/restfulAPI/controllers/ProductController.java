package com.example.demo.controllers;

import com.example.demo.MailSender.service.MailSenderService;
import com.example.demo.dto.ProductDTO;
import com.example.demo.models.Product;
import com.example.demo.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    private final MailSenderService mailSenderService;

    @GetMapping("/alive")
    String start() {
        return "server is alive on port";
    }

    @PostMapping("/all/email/html")
    public ResponseEntity<List<Product>> sendHtmlEmail() {
        var allProducts = productService.readAllProducts();
        String htmlContent = getHTMLViewFromString(allProducts);

        try {
            mailSenderService.sendHtmlMail(
                    "robert9batouan@gmail.com",
                    "List of all products",
                    htmlContent);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(allProducts, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(allProducts);
    }

    private static String getHTMLViewFromString(List<Product> allProducts) {
        String htmlContent = "<html><head><style>"
                + "body {font-family: Arial, sans-serif;}"
                + "h1 {color: #5a9;}"
                + ".product {border: 1px solid #ddd; margin: 15px; padding: 15px;}"
                + "</style></head><body>"
                + "<h1>Hey!</h1><p>There's all products in my storage system:</p>";

        for (var product : allProducts) {
            htmlContent += "<div class=\"product\"><p>" + product + "</p></div>";
        }

        htmlContent += "</body></html>";
        return htmlContent;
    }

    @PostMapping("/all/email")
    public ResponseEntity<List<Product>> sendEmail() {
        var allProducts = productService.readAllProducts();
        try {
            mailSenderService.sendToOne(
                    "robert9batouan@gmail.com",
                    "Hey!\n There's all products in my storage system:\n",
                    allProducts.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(allProducts, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(allProducts);
    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.readAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        var product = productService.readProduct(id);
        if (product.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

    @PostMapping("/add")
    @CrossOrigin
    public ResponseEntity<Product> addProduct(@RequestBody ProductDTO dto) {
        productService.createProduct(dto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDTO dto) {
        productService.updateProduct(dto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> deleteProductById(@PathVariable Long id) {
        productService.deleteProduct(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}