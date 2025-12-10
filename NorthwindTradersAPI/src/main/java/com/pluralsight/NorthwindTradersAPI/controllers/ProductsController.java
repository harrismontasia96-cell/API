package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.dao.ProductDao;
import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ProductDao productDao;


    public ProductsController(ProductDao productDao) {
        this.productDao = productDao;
    }



    @GetMapping("")
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable int id) {
        return productDao.getById(id);
    }


    @PostMapping("")
    public Product insert(@RequestBody Product product) {
        return productDao.insert(product);
    }
}