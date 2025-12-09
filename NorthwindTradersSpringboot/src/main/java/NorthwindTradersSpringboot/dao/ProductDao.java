package com.northwindtraders.dao;

import com.northwindtraders.models.Product;
import java.util.List;

public interface ProductDao {
    List<Product> getAll();
    Product getById(int id);
    void add(Product product);
    void update(Product product);
    void delete(int id);
}