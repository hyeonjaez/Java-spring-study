package com.nhnacademy.shoppingmall.product.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.product.domain.Category;
import com.nhnacademy.shoppingmall.product.repository.CategoryRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CategoryRepositoryImpl implements CategoryRepository {
    @Override
    public List<Category> categoryList() {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from Categories";
        ResultSet rs = null;
        List<Category> categoryList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            rs = statement.executeQuery();
            while (rs.next()) {
                categoryList.add(new Category(rs.getInt("CategoryID"), rs.getString("CategoryName")));
            }
            return categoryList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (Objects.nonNull(rs)) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    public Optional<Category> findCategoryById(int categoryId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from Categories where CategoryID = ?";
        ResultSet rs = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, categoryId);
            rs = statement.executeQuery();

            if (rs.next()) {
                return Optional.of(new Category(rs.getInt("CategoryID"), rs.getString("CategoryName")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (Objects.nonNull(rs)) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return Optional.empty();
    }
}
