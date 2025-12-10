package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.dao.CategoryDao;
import com.pluralsight.NorthwindTradersAPI.models.Category;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    private final CategoryDao categoryDao;

    public CategoriesController(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }


    @GetMapping("")
    public List<Category> getAll() {
        return categoryDao.getAll();
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable int id) {
        return categoryDao.getById(id);
    }


    @PostMapping("")
    public Category insert(@RequestBody Category category) {
        return categoryDao.insert(category);
    }
}