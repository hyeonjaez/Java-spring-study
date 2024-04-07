package com.nhnacademy.shoppingmall.common.util;


import java.sql.SQLException;
import java.time.Duration;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class DbUtils {
    private DbUtils() {
        throw new IllegalStateException("Constructor access restrictions");
    }

    private static final DataSource DATASOURCE;

    static {
        BasicDataSource basicDataSource = new BasicDataSource();

        try {
            basicDataSource.setDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        basicDataSource.setUrl("jdbc:mysql://133.186.241.167:3306/nhn_academy_47");
        basicDataSource.setUsername("nhn_academy_47");
        basicDataSource.setPassword("([wn5DuzeGlRiy)R");


        basicDataSource.setInitialSize(5);
        basicDataSource.setMaxTotal(5);
        basicDataSource.setMaxIdle(5);
        basicDataSource.setMinIdle(5);


        basicDataSource.setMaxWait(Duration.ofSeconds(2));
        basicDataSource.setValidationQuery("select 1");
        basicDataSource.setTestOnBorrow(true);
        DATASOURCE = basicDataSource;

    }

    public static DataSource getDataSource() {
        return DATASOURCE;
    }

}
