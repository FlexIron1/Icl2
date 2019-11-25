package com.example.methods;


import com.example.connect.ConnectDb;


import java.io.IOException;
import java.sql.*;

public class CopyByID {

    public void id(int id) throws SQLException {

        ConnectDb connectDb = new ConnectDb();

        String sql = "INSERT INTO postgres2.public.table_name2  (id,column_2,column_3,column_4,column_5)" +
                "VALUES(?,?,?,?,?)" + "ON CONFLICT (id) DO NOTHING";
        String sql2 = "SELECT *  FROM table_name where id = ?";

        Connection ctDbPostgres = null;

        try {
            System.out.println("Подключение к postgres");
            ctDbPostgres = connectDb.connect("demo");
            System.out.println("Вы удачно подключились к базе данных postgres");
        } catch (IOException e) {
            System.out.println("Ошибка подключения к базе данных postgres");
        }

        assert ctDbPostgres != null;
        PreparedStatement preparedStatement = ctDbPostgres.prepareStatement(sql2);

        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        Connection ctDbPostgres2 = null;
        try {
            System.out.println("Подключение к postgres2");
            ctDbPostgres2 = connectDb.connect("demo2");
            System.out.println("Вы удачно подключились к базе данных postgres2");
        } catch (IOException e) {
            System.out.println("Ошибка подключения к базе данных postgres2");
        }

        assert ctDbPostgres2 != null;
        PreparedStatement ps = ctDbPostgres2.prepareStatement(sql);

        new Result(resultSet, ps);
        try {
            resultSet.close();
            preparedStatement.close();
            ps.close();
            ctDbPostgres.close();
            ctDbPostgres2.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ошибка" + ex.getMessage());
        }

    }


}





