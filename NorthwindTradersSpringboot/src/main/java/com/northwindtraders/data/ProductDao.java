package com.northwindtraders.data;



import com.example.NorthwindTradersSpringboot.models.Product;
import java.util.List;

public interface ProductDao {
    void add(Product product);
    List<Product> getAll();
}