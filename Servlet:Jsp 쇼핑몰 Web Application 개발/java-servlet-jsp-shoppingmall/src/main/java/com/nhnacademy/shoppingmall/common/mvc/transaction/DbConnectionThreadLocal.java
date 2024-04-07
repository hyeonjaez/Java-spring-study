package com.nhnacademy.shoppingmall.common.mvc.transaction;

import com.nhnacademy.shoppingmall.common.util.DbUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DbConnectionThreadLocal {
    private static final ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Boolean> sqlErrorThreadLocal = ThreadLocal.withInitial(() -> false);

    public static void initialize() {

        try {
            Connection connection = DbUtils.getDataSource().getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
            connectionThreadLocal.set(connection);

        } catch (SQLException e) {
            log.error("initalize() error : {}", e.getMessage());
        }
    }

    public static Connection getConnection() {
        return connectionThreadLocal.get();
    }

    public static void setSqlError(boolean sqlError) {
        sqlErrorThreadLocal.set(sqlError);
    }

    public static boolean getSqlError() {
        return sqlErrorThreadLocal.get();
    }

    public static void reset() {
        Connection connection = connectionThreadLocal.get();

        try {

            if (getSqlError()) {
                if (Objects.nonNull(connection)) {
                    connection.rollback();
                }
            } else {
                if (Objects.nonNull(connection)) {
                    connection.commit();
                }
            }
            if (Objects.nonNull(connection) && !connection.isClosed()) {
                connection.close();
            }

            connectionThreadLocal.remove();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private DbConnectionThreadLocal() {
        throw new RuntimeException("Constructor access restrictions");
    }
}
