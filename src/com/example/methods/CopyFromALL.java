package com.example.methods;


import com.example.connect.ConnectFromDb;

import org.postgresql.util.PSQLException;

import java.io.IOException;
import java.sql.*;

public class CopyFromALL {


    public void all() throws SQLException, IOException {

        ConnectFromDb connectFromDb = new ConnectFromDb();

        Statement statement;
        ResultSet resultSet;

        Connection ctDbPostgres;
        Connection ctDbPostgres2;

        String sql = "INSERT INTO postgres2.public.table_name  (id,column_2,column_3,column_4,column_5)"
                     + "VALUES(?,?,?,?,?)" +
                     "ON CONFLICT (id) DO NOTHING ";
        String sql2 = "SELECT *FROM postgres.public.table_name";

        ctDbPostgres = connectFromDb.connect("demo");

        statement = ctDbPostgres.createStatement();

        resultSet = statement
                .executeQuery(sql2);

        ctDbPostgres2 = connectFromDb.connect("demo2");

        PreparedStatement preparedStatement = ctDbPostgres2.prepareStatement(sql);
        try {
            new Result(resultSet, preparedStatement);
        } catch (PSQLException p) {

            System.out.println("Такие значения уже существуют в базе данных!");
        }
        try {
            resultSet.close();
            statement.close();
            ctDbPostgres.close();
            ctDbPostgres2.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ошибка " + ex.getMessage());
        }

    }

}


