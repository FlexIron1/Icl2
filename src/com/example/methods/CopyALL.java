package com.example.methods;


import com.example.connect.ConnectDb;

import org.postgresql.util.PSQLException;

import java.io.IOException;
import java.sql.*;

public class CopyALL {

    public void all() throws SQLException {

        ConnectDb connectDb = new ConnectDb();

        String sql = "INSERT INTO postgres2.public.table_name2  (id,column_2,column_3,column_4,column_5)" +
                "VALUES(?,?,?,?,?)ON CONFLICT (id) DO NOTHING ";
        String sql2 = "SELECT *FROM postgres.public.table_name";

        Connection ctDbPostgres = null;

        try {
            ctDbPostgres = connectDb.connect("demo");
            System.out.println("Вы удачно подключились к базе данных postgres");
        } catch (IOException e) {
            System.out.println("Ошибка подключения к базе данных postgres");
        }

        Statement statement = null;
        if (ctDbPostgres != null) {
            statement = ctDbPostgres.createStatement();
        } else {
            System.out.println("Вы не подключены к базе данных postgres");
        }

        ResultSet resultSet = null;
        if (statement != null) {
            resultSet = statement.executeQuery(sql2);
        } else {
            System.out.println("У вас нету записанного Statement");
        }

        Connection ctDbPostgres2 = null;
        try {
            ctDbPostgres2 = connectDb.connect("demo2");
            System.out.println("Вы удачно подключились к базе данных postgres2");
        } catch (IOException e) {
            System.out.println("Ошибка подключения к базе данных postgres2");
        }

        PreparedStatement preparedStatement = null;
        if (ctDbPostgres2 != null) {
            preparedStatement = ctDbPostgres2.prepareStatement(sql);
        } else {
            System.out.println("Вы не подключены к базе данных postgres2");
        }

        try {
            if (resultSet != null) {
                new Result(resultSet, preparedStatement);
            } else {
                System.out.println("Нету результата SQL запроса");
            }
        } catch (PSQLException p) {

            System.out.println("Такие значения уже существуют в базе данных!");
        }

        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (ctDbPostgres != null) {
                ctDbPostgres.close();
            }
            if (ctDbPostgres2 != null) {
                ctDbPostgres2.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ошибка " + ex.getMessage());
        }

    }

}


