package com.meuedu.xm.utils;

import java.sql.*;

public class JDBCUtils {
    private static String Driver="org.apache.hive.jdbc.HiveDriver";
    private static String url="jdbc:hive2://192.168.64.110:10000/default";
    static{
        try {
            Class.forName(Driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,"root","123456");
    }
    public static void close(Connection connection,Statement statement) throws SQLException {
        if(connection!=null){
            connection.close();
        }
        if(statement!=null){
            statement.close();
        }
    }
    public static void close(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        if(connection!=null){
            connection.close();
        }
        if(statement!=null){
            statement.close();
        }
        if(resultSet!=null){
            resultSet.close();
        }
    }
}
