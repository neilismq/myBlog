package com.bj.zzq.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @Author: zhaozhiqiang
 * @Date: 2018/12/12
 * @Description:
 */
public class JdbcUtils {
    //连接数据库
    public static Connection getConnection() throws Exception {
        String driverClass = null;
        String url = null;
        String user = null;
        String password = null;

        Properties properties = new Properties();
        InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("application.properties");
        properties.load(in);
        driverClass = properties.getProperty("spring.datasource.driver-class-name");
        url = properties.getProperty("spring.datasource.url");
        user = properties.getProperty("spring.datasource.username");
        password = properties.getProperty("spring.datasource.password");
        Class.forName(driverClass);
        return DriverManager.getConnection(url, user, password);
    }

    //关闭数据库连接
    public static void release(Connection con, Statement state) {
        if (state != null) {
            try {
                state.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //关闭数据库连接
    public static void release(ResultSet rs, Connection con, Statement state) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (state != null) {
            try {
                state.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Double aDouble = new Double(2.1);
        System.out.println(aDouble == 2.1);
        float a = 1.00f;
        System.out.println(a==1d);
    }
}
