package com.example.connect;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectDb {

    public Connection connect(String property) throws IOException, SQLException {

        InputStream input = new FileInputStream("src\\resource\\" + property + ".properties");

        Properties s = new Properties();
        s.load(input);

        final String URL = s.getProperty("url");
        final String USER_NAME = s.getProperty("username");
        final String PASSWORD = s.getProperty("password");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Не удалось найти драйвер JDBC ");
            e.printStackTrace();
        }
        Connection connection;
        connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        return connection;
    }
}
