package com.nhnacademy.shoppingmall.product.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public Optional<Product> findProductById(int productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from Products where ProductID = ?";
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Product product = new Product(resultSet.getInt("ProductID"),
                        resultSet.getString("ModelNumber"),
                        resultSet.getString("ModelName"),
                        resultSet.getString("ProductImage"),
                        resultSet.getLong("UnitCost"),
                        resultSet.getString("Description"));
                return Optional.of(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (Objects.nonNull(resultSet)) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return Optional.empty();
    }

    @Override
    public int save(Product product) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql =
                "insert into Products (ModelNumber, ModelName, ProductImage, UnitCost, Description) values(?,?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getModelNumber());
            statement.setString(2, product.getModelName());
            statement.setString(3, product.getProductImage());
            statement.setLong(4, product.getUnitCost());
            statement.setString(5, product.getDescription());

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteByProductId(int productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from Products where ProductID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);

            return statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Product product) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql =
                "update Products set ModelNumber=?, ModelName =?, ProductImage =?, UnitCost =?, Description =? where ProductID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getModelNumber());
            statement.setString(2, product.getModelName());
            statement.setString(3, product.getProductImage());
            statement.setLong(4, product.getUnitCost());
            statement.setString(5, product.getDescription());
            statement.setInt(6, product.getProductId());

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int productTotalCount() {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from Products";
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public List<Product> findAllProduct() {
        Connection connection = DbConnectionThreadLocal.getConnection();
        List<Product> productList = new ArrayList<>();
        String sql = "select * from Products";
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            rs = statement.executeQuery();
            while (rs.next()) {
                productList.add(new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ModelNumber"),
                        rs.getString("ModelName"),
                        rs.getString("ProductImage"),
                        rs.getLong("UnitCost"),
                        rs.getString("Description")));
            }
            return productList;
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
    public int countByProductId(int productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select COUNT(*) from Products where ProductID = ?";
        ResultSet rs = null;
        int result = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
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
    public Page<Product> findProductByCategory(int categoryId, int page, int pageSize) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        int offset = (page - 1) * pageSize;
        int limit = pageSize;
        String sql = "SELECT Products.* FROM Products " +
                "INNER JOIN Product_Categories ON Products.ProductID = Product_Categories.ProductID " +
                "WHERE Product_Categories.CategoryID = ? " +
                "ORDER BY ProductID ASC LIMIT ?, ?";
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, categoryId);
            statement.setInt(2, offset);
            statement.setInt(3, limit);
            rs = statement.executeQuery();
            List<Product> productList = new ArrayList<>();

            while (rs.next()) {
                productList.add(new Product(rs.getInt("ProductID"),
                        rs.getString("ModelNumber"),
                        rs.getString("ModelName"),
                        rs.getString("ProductImage"),
                        rs.getLong("UnitCost"),
                        rs.getString("Description")));
            }

            return new Page<>(productList, pageSize, countProductByCategoryId(categoryId));
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
    public Page<Product> findAll(int page, int pageSize) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        int offset = (page - 1) * pageSize;
        int limit = pageSize;
        ResultSet rs = null;

        String sql = "select * from Products order by ProductID asc limit ?,?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, offset);
            statement.setInt(2, limit);
            rs = statement.executeQuery();
            List<Product> productList = new ArrayList<>();

            while (rs.next()) {
                productList.add(new Product(rs.getInt("ProductID"),
                        rs.getString("ModelNumber"),
                        rs.getString("ModelName"),
                        rs.getString("ProductImage"),
                        rs.getLong("UnitCost"),
                        rs.getString("Description")));
            }

            return new Page<>(productList, pageSize, productTotalCount());
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
    public int countProductByCategoryId(int categoryId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select COUNT(ProductId) from Product_Categories where CategoryID =?";
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, categoryId);
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

    @Override
    public int getLastIdNumber() {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select LAST_INSERT_ID()";
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