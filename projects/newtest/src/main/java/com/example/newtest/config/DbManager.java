package com.example.newtest.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager {

    public Connection createConnection () throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jersey","postgres","root");
        System.out.println(connection);
        return connection;
    }
}
