package com.lihd.java.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyJdbc {


    private static Connection getConnection() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver ");

        String url = "";

        String user = "";

        String password = "";

        return DriverManager.getConnection(url, user, password);

    }


    public static void main(String[] args) throws ClassNotFoundException {


        @SuppressWarnings({"SqlNoDataSourceInspection", "SqlDialectInspection"})
        String sql = " select * from VZX_ITV_BQ_ZW where 1=1  " ;


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
