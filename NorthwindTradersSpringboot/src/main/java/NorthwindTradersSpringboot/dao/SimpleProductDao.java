package com.example.NorthwindTradersSpringboot.dao;

import com.example.NorthwindTradersSpringboot.models.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SimpleProductDao implements ProductDao {

    private List<Product> products = new ArrayList<>();

    public SimpleProductDao() {
        // preload example products in memory
        products.add(new Product(1, "Laptop", "Electronics", 999.99));
        products.add(new Product(2, "Desk Lamp", "Home", 29.99));
        products.add(new Product(3, "Notebook", "Office", 3.49));
    }

    @Override
    public void add(Product product) {
        products.add(product);
    }

    @Override
    public List<Product> getAll() {
        return products;
    }
}


