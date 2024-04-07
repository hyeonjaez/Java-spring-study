package com.nhnacademy.shoppingmall.user.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.user.domain.UserAddress;
import com.nhnacademy.shoppingmall.user.repository.UserAddressRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserAddressRepositoryImpl implements UserAddressRepository {
    @Override
    public List<UserAddress> getUserAddressBy(String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from UserAddress where user_id = ?";
        ResultSet rs = null;
        List<UserAddress> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userId);
            rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new UserAddress(rs.getInt("AddressID"), rs.getString("user_id"), rs.getString("AddressName")));
            }
            return list;
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
    public int deleteAddressByUserId(String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from UserAddress where user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userId);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int saveAddress(UserAddress userAddress) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into UserAddress (user_id, AddressName) values (?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userAddress.getUserId());
            statement.setString(2, userAddress.getAddressName());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
