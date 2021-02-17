package com.example.bankFinal.DbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconn {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class<?> jdbcClasss = Class.forName("org.postgresql.Driver");
        System.out.println(jdbcClasss);
        String user = "postgres";
        String pass = "password";
        String url = "jdbc:postgresql://localhost:5432/bank";
        Connection connection = DriverManager.getConnection(url, user, pass);
        if (connection.isValid(2)) {
            System.out.println("connected");
        }else {
            System.out.println("does not connected");
        }
        return connection;
    }
}
