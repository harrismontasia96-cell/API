package com.northwindtraders.data;

import com.northwindtraders.dao.ProductDao;
import com.northwindtraders.models.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Repository
public class JdbcProductDao implements ProductDao {

    private final JdbcTemplate jdbc;

    @Autowired
    public JdbcProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    private RowMapper<Product> productMapper = (rs, rowNum) -> {
        Product p = new Product();
        p.setProductId(rs.getInt("ProductID"));
        p.setProductName(rs.getString("ProductName"));
        p.setCategory(rs.getString("Category"));
        p.setPrice(rs.getDouble("Price"));
        return p;
    };

    @Override
    public List<Product> getAll() {
        String sql = "SELECT ProductID, ProductName, Category, Price FROM Products";
        return jdbc.query(sql, productMapper);
    }

    @Override
    public Product getById(int id) {
        String sql = "SELECT ProductID, ProductName, Category, Price FROM Products WHERE ProductID = ?";
        return jdbc.queryForObject(sql, productMapper, id);
    }

    @Override
    public void add(Product product) {
        String sql = "INSERT INTO Products (ProductName, Category, Price) VALUES (?, ?, ?)";
        jdbc.update(sql,
                product.getProductName(),
                product.getCategory(),
                product.getPrice()
        );
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE Products SET ProductName = ?, Category = ?, Price = ? WHERE ProductID = ?";
        jdbc.update(sql,
                product.getProductName(),
                product.getCategory(),
                product.getPrice(),
                product.getProductId()
        );
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Products WHERE ProductID = ?";
        jdbc.update(sql, id);
    }
}


