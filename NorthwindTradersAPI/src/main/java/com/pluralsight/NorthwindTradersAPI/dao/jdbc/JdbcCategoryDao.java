package com.pluralsight.NorthwindTradersAPI.dao.jdbc;

import com.pluralsight.NorthwindTradersAPI.dao.CategoryDao;
import com.pluralsight.NorthwindTradersAPI.models.Category;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCategoryDao implements CategoryDao {

    private final DataSource dataSource;

    public JdbcCategoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT CategoryID, CategoryName FROM categories";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                categories.add(new Category(
                        rs.getInt("CategoryID"),
                        rs.getString("CategoryName")
                ));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return categories;
    }

    @Override
    public Category getById(int id) {
        String sql = "SELECT CategoryID, CategoryName FROM categories WHERE CategoryID = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Category(
                        rs.getInt("CategoryID"),
                        rs.getString("CategoryName")
                );
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Category insert(Category category) {
        String sql = "INSERT INTO categories (CategoryName) VALUES (?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, category.getCategoryName());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    category.setCategoryId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return category;
    }
}

