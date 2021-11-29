package com.example.newtest.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
    private static final String dbBase = "jdbc:mysql://localhost:";
    private static final String dbType = "mysql";
    private static final String port = "3306";
    private  static final String dbName = "jersey";

//    .....................................................

    private static final String url =  dbBase + port + "/" + dbName;
    private static final String username = "root";
    private static final String password ="root";

    public static Connection getConnection () throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection(url,username,password);
        return connection;
    }


}
