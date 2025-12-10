package com.pluralsight.NorthwindTradersAPI.controllers;


import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private List<Product> products = new ArrayList<>();

    public ProductsController() {
        products.add(new Product(1, "Chai", 1, 18.00));
        products.add(new Product(2, "Chang", 1, 19.00));
        products.add(new Product(3, "Aniseed Syrup", 2, 10.00));
        products.add(new Product(5, "Chef Anton's Gumbo Mix", 2, 21.35));
    }

    // GET /products
    @GetMapping
    public List<Product> getAllProducts() {
        return products;
    }

    // GET /products/{id}
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return products.stream()
                .filter(p -> p.getProductId() == id)
                .findFirst()
                .orElse(null);
    }
}

