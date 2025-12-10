package com.pluralsight.NorthwindTradersAPI.dao.jdbc;

import com.pluralsight.NorthwindTradersAPI.dao.ProductDao;
import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProductDao implements ProductDao {

    private final DataSource dataSource;

    public JdbcProductDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT ProductID, ProductName, CategoryID, UnitPrice FROM products";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getInt("CategoryID"),
                        rs.getDouble("UnitPrice")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    @Override
    public Product getById(int id) {
        String sql = "SELECT ProductID, ProductName, CategoryID, UnitPrice FROM products WHERE ProductID = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getInt("CategoryID"),
                        rs.getDouble("UnitPrice")
                );
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(int id, Product product) {
        String sql = "UPDATE products SET ProductName = ?, CategoryID = ?, UnitPrice = ? WHERE ProductID = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getProductName());
            stmt.setInt(2, product.getCategoryId());
            stmt.setDouble(3, product.getUnitPrice());
            stmt.setInt(4, id);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}