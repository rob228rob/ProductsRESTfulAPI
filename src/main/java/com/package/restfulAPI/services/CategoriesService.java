package com.example.demo.services;

import com.example.demo.models.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoriesService {
    List<Category> categories;
    ArrayList<String> list = new ArrayList<String>();

    void addCategory(Category category) {
        categories.add(category);
    }

    List<Category> getAllCategories() {
        return categories;
    }

    Category getCategoryById(int id) {
        return categories.get(id);
    }
}
