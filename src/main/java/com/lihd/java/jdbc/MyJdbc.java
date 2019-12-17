package com.lihd.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyJdbc {


    private static Connection getConnection() throws SQLException {

        String url = "jdbc:mysql://192.168.160.129:3306/lihd?useSSL=false";

        String user = "root";

        String password = "root";

        return DriverManager.getConnection(url, user, password);

    }


    public static void main(String[] args)  {


        @SuppressWarnings({"SqlNoDataSourceInspection", "SqlDialectInspection"})
        String sql = " select * from runoob_tbl where 1=1  " ;


        ResultSet rs = null;
        PreparedStatement ps = null;
        int count = 0;
        Connection connection = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
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
