package com.restfulAPI.MVCProduct.Controllers;

import com.restfulAPI.MVCProduct.Models.ProductModel;
import com.restfulAPI.dto.ProductDTO;
import com.restfulAPI.models.Product;
import com.restfulAPI.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductMVCController {

    private final ProductService productService;

    public ProductMVCController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = productService.readAllProducts();
        model.addAttribute("products", products);
        return "products.html";  // Указываем имя Thymeleaf шаблона с расширением .html
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        var product = productService.readProduct(id);
        if (product.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Product not found");
            return "redirect:/products";
        }
        model.addAttribute("product", product.get());
        return "product-details.html";  // Указываем имя Thymeleaf шаблона с расширением .html
    }

    @GetMapping("/new")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product.html";  // Указываем имя Thymeleaf шаблона с расширением .html
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute ProductDTO dto, RedirectAttributes redirectAttributes) {
        productService.createProduct(dto);
        redirectAttributes.addFlashAttribute("successMessage", "Product added successfully");
        return "redirect:/products";
    }

    @GetMapping("/update/{id}")
    public String showUpdateProductForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Product> product = productService.readProduct(id);
        if (product.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Product not found");
            return "redirect:/products";
        }
        model.addAttribute("product", product.get());
        return "update-product.html";  // Указываем имя Thymeleaf шаблона с расширением .html
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        productService.updateProduct(product);
        redirectAttributes.addFlashAttribute("successMessage", "Product updated successfully");
        return "redirect:/products";
    }

    @PostMapping("/delete/{id}")
    public String deleteProductById(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        productService.deleteProduct(id);
        redirectAttributes.addFlashAttribute("successMessage", "Product deleted successfully");
        return "redirect:/products";
    }
}