package com.nhnacademy.shoppingmall.user.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserRepositoryImpl implements UserRepository {

    @Override
    public Optional<User> findByUserIdAndUserPassword(String userId, String userPassword) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql =
                "select user_id, user_name, user_password, user_birth, user_auth, user_point, created_at, latest_login_at from users where user_id = ? and user_password = ?";

        log.debug("sql:{}", sql);
        ResultSet rs = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, userPassword);

            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                User user = new User(rs.getString("user_id"),
                        rs.getString("user_name"),
                        rs.getString("user_password"),
                        rs.getString("user_birth"),
                        User.Auth.valueOf(rs.getString("user_auth")),
                        rs.getInt("user_point"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        Objects.nonNull(rs.getTimestamp("latest_login_at")) ?
                                rs.getTimestamp("latest_login_at").toLocalDateTime() : null);
                return Optional.of(user);
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

    @Override
    public Optional<User> findById(String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql =
                "select user_id, user_name, user_password, user_birth, user_auth, user_point, created_at, latest_login_at from users where user_id = ?";

        log.debug("sql:{}", sql);
        ResultSet rs = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userId);

            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                User user = new User(rs.getString("user_id"),
                        rs.getString("user_name"),
                        rs.getString("user_password"),
                        rs.getString("user_birth"),
                        User.Auth.valueOf(rs.getString("user_auth")),
                        rs.getInt("user_point"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        Objects.nonNull(rs.getTimestamp("latest_login_at")) ?
                                rs.getTimestamp("latest_login_at").toLocalDateTime() : null);
                return Optional.of(user);
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

    @Override
    public int save(User user) {
        Connection connection = DbConnectionThreadLocal.getConnection();

        String sql = "INSERT INTO users VALUES (?, ?, ?,?,?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUserId());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getUserPassword());
            statement.setString(4, user.getUserBirth());
            statement.setString(5, user.getUserAuth().toString());
            statement.setInt(6, user.getUserPoint());
            statement.setObject(7, user.getCreatedAt());
            statement.setObject(8, Objects.nonNull(user.getLatestLoginAt()) ? user.getLatestLoginAt() : null);

            int result = statement.executeUpdate();

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public int deleteByUserId(String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from users where user_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userId);

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(User user) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql =
                "update users set user_name=?, user_password =?, user_birth =?, user_auth =?, user_point=?, created_at =?, latest_login_at =? where user_id =?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getUserName());
            statement.setString(2, user.getUserPassword());
            statement.setString(3, user.getUserBirth());
            statement.setString(4, user.getUserAuth().toString());
            statement.setInt(5, user.getUserPoint());
            statement.setObject(6, user.getCreatedAt());
            statement.setObject(7,
                    Objects.nonNull(user.getLatestLoginAt()) ? user.getLatestLoginAt() : null);
            statement.setString(8, user.getUserId());
            return statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateLatestLoginAtByUserId(String userId, LocalDateTime latestLoginAt) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "update users set latest_login_at = ? where user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, latestLoginAt);
            statement.setString(2, userId);

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByUserId(String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select COUNT(*) from users where user_id = ?";
        ResultSet rs = null;
        int result = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userId);
            rs = statement.executeQuery();
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

    @Override
    public Page<User> userPage(int page, int pageSize) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        int offset = (page - 1) * pageSize;
        int limit = pageSize;

        ResultSet rs = null;
        String sql = "select * from users order by user_id asc limit ?,?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, offset);
            statement.setInt(2, limit);
            rs = statement.executeQuery();
            List<User> userList = new ArrayList<>();

            while (rs.next()) {
                userList.add(new User(rs.getString("user_id"),
                        rs.getString("user_name"),
                        rs.getString("user_password"),
                        rs.getString("user_birth"),
                        User.Auth.valueOf(rs.getString("user_auth")),
                        rs.getInt("user_point"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        Objects.nonNull(rs.getTimestamp("latest_login_at")) ?
                                rs.getTimestamp("latest_login_at").toLocalDateTime() : null));
            }
            return new Page<>(userList, pageSize, countUser());
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

    private int countUser() {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select COUNT(*) from users";
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
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
