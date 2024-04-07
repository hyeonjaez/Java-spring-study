package com.nhnacademy.shoppingmall.shoppingcart.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.shoppingcart.domain.ShoppingCart;
import com.nhnacademy.shoppingmall.shoppingcart.repository.ShoppingCartRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShoppingCartRepositoryImpl implements ShoppingCartRepository {
    @Override
    public List<ShoppingCart> getShoppingCart(String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from ShoppingCart where user_id = ?";
        ResultSet rs = null;
        List<ShoppingCart> shoppingCartList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userId);
            rs = statement.executeQuery();
            while (rs.next()) {
                shoppingCartList.add(new ShoppingCart(rs.getString("ProductID"), rs.getString("user_id"),
                        rs.getInt("amount")));
            }

            return shoppingCartList;
        } catch (SQLException e) {
            System.out.println(e);
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
    public int saveShoppingCart(ShoppingCart shoppingCart) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into ShoppingCart (ProductID, user_id, amount) values(?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, shoppingCart.getProductId());
            statement.setString(2, shoppingCart.getUserId());
            statement.setInt(3, shoppingCart.getAmount());

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteShoppingCart(ShoppingCart shoppingCart) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from ShoppingCart where ProductID =? and user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, shoppingCart.getProductId());
            statement.setString(2, shoppingCart.getUserId());

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateShoppingCartAmount(ShoppingCart shoppingCart) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "update ShoppingCart set amount =? where ProductID =? and user_id =?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, shoppingCart.getAmount());
            statement.setString(2, shoppingCart.getProductId());
            statement.setString(3, shoppingCart.getUserId());

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countShoppingCart(ShoppingCart shoppingCart) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select COUNT(*) from ShoppingCart where ProductID =? and user_id = ?";
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, shoppingCart.getProductId());
            statement.setString(2, shoppingCart.getUserId());
            rs = statement.executeQuery();
            int result = 0;
            if (rs.next()) {
                result = rs.getInt(1);
            }

            return result;
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
}
