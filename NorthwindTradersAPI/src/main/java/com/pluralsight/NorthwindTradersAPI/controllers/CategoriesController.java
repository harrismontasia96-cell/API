package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.models.Category;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/categories")
public class CategoriesController {

    private List<Category> categories = new ArrayList<>();

    public CategoriesController() {
        categories.add(new Category(1, "Beverages"));
        categories.add(new Category(2, "Condiments"));
        categories.add(new Category(17, "Meats"));
    }


    @GetMapping
    public List<Category> getAllCategories() {
        return categories;
    }


    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable int id) {
        return categories.stream()
                .filter(c -> c.getCategoryId() == id)
                .findFirst()
                .orElse(null);
    }
}

