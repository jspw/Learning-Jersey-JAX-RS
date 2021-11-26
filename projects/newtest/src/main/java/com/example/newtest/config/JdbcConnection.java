package com.example.newtest.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
    private static final String dbBase = "jdbc:postgresql://localhost:";
    private static final String dbType = "postgresql";
    private static final String port = "5432";
    private  static final String dbName = "jersey";

//    .....................................................

    private static final String url =  dbBase + port + "/" + dbName;
    private static final String username =      "postgres";
    private static final String password =   "root";

    public static Connection getConnection () throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection(url,username,password);
        return connection;
    }


}
