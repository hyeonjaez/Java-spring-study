package com.nhnacademy.shoppingmall.product.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.product.repository.CategoryProductRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoryProductRepositoryImpl implements CategoryProductRepository {

    @Override
    public List<Integer> findCategoryId(int productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select CategoryID from Product_Categories where ProductID = ?";
        List<Integer> categoryIdList = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            rs = statement.executeQuery();
            while (rs.next()) {
                categoryIdList.add(rs.getInt(1));
            }
            return categoryIdList;
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
    public List<Integer> findProductId(int categoryId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select ProductID from Product_Categories where CategoryID = ?";
        List<Integer> categoryIdList = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, categoryId);
            rs = statement.executeQuery();
            while (rs.next()) {
                categoryIdList.add(rs.getInt(1));
            }
            return categoryIdList;
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
    public int addCategoryProduct(int categoryId, int productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into Product_Categories value(?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, categoryId);
            statement.setInt(2, productId);

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteCategoryProduct(int productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from Product_Categories where ProductID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
