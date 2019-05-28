package com.lihd.java.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyJdbc {


    private static Connection getConnection() throws SQLException, ClassNotFoundException {

        Class.forName("oracle.jdbc.OracleDriver");

        String url = "jdbc:oracle:thin:@132.228.27.132:1521/css";

        String user = "CSS_REPORT";

        String password = "68m8NaV_yNTz";

        return DriverManager.getConnection(url, user, password);

    }


    public static void main(String[] args) throws ClassNotFoundException {


        String sql = " select * from VZX_ITV_BQ_ZW where 1=1 and ( ( VZX_ITV_BQ_ZW.产品ID < 200000000000000000000000000 and VZX_ITV_BQ_ZW.产品ID > 0 ) )  " ;


        ResultSet rs = null;
        PreparedStatement ps = null;
        List<Map<String, Object>> mapList = new ArrayList<>();
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
            } catch (SQLException e) {
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
    }

}
