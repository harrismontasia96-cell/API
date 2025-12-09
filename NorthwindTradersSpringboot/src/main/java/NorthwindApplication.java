package com.northwindtraders;

import com.northwindtraders.dao.ProductDao;
import com.northwindtraders.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class NorthwindApplication implements CommandLineRunner {

    @Autowired
    private ProductDao productDao;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("=== Northwind Traders CLI ===");

        System.out.println("Products in database:");
        productDao.getAll().forEach(product -> {
            System.out.println(product.getProductId() + " - " + product.getProductName());
        });
    }
}