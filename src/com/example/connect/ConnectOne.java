package com.example.connect;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectOne {

    public Connection connect(String property) throws IOException {


        InputStream input = new FileInputStream("C:\\Users\\Samat\\IdeaProjects\\Icl2\\src\\resource\\" + property + ".properties");

        Properties s = new Properties();

        s.load(input);
        final String URL = s.getProperty("spring.datasource.url");

        final String USER_NAME = s.getProperty("spring.datasource.username");

        final String PASSWORD = s.getProperty("spring.datasource.password");


        System.out.println("Подключение к базе данных...");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Не удалось найти драйвер JDBC ");
            e.printStackTrace();
        }

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);

        } catch (SQLException e) {
            System.out.println("Ошибка подключения");
            e.printStackTrace();
            return connection;
        }

        if (connection != null) {
            System.out.println("Вы удачно подключились к базе данных");
        } else {
            System.out.println("Не удалось установить соединение с базой данных");
        }
        return connection;
    }

    public static void main(String[] args) throws IOException {
        ConnectOne connectToPostgreSQL = new ConnectOne();
        connectToPostgreSQL.connect("demo");
    }
}
