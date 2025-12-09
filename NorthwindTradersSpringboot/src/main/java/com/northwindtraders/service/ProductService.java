package com.northwindtraders.service;

import com.northwindtraders.dao.ProductDao;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ProductService {

    private final ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void printAllProducts() {
        productDao.getAllProducts().forEach(System.out::println);
    }
}


