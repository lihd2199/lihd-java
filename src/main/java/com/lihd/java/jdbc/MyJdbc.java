package com.lihd.java.jdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyJdbc {


    private static Connection getConnection() throws SQLException {

        String url = "jdbc:mysql://192.168.160.130:3306/lihd?useSSL=false";

        String user = "root";

        String password = "123456";

        return DriverManager.getConnection(url, user, password);

    }


    @Test
    public void testTransaction() {


    }

    @Test
    public void testInsert() throws SQLException {

        String sql = " insert into name (name,valie) values (?,?)";

        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(sql);

            for (int i = 0; i < 10; i++) {
                ps.setObject(1, "name" + 1);
                ps.setObject(2, "value" + 1);
                ps.addBatch();
            }
            ps.executeBatch();
            connection.commit();

        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            e.printStackTrace();
        } finally {
            release(rs, connection, ps);
        }


    }


    @Test
    public void query() {

        @SuppressWarnings({"SqlNoDataSourceInspection", "SqlDialectInspection"})
        String sql = " select * from name where 1=1  ";


        ResultSet rs = null;
        PreparedStatement ps = null;
        int count = 0;
        Connection connection = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            release(rs, connection, ps);
        }

        System.out.println(count);

    }

    private static void release(ResultSet resultSet, Connection connection, Statement stmt) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ignore) {
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ignore) {
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ignore) {
            }
        }
    }

}
