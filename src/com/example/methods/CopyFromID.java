package com.example.methods;


import com.example.connect.ConnectFromDb;


import java.io.IOException;
import java.sql.*;

public class CopyFromID {

    public void id(int id) throws IOException, SQLException {

        ConnectFromDb connectFromDb = new ConnectFromDb();

        Connection ctDbPostgres;
        Connection ctDbPostgres2;

        String sql = "INSERT INTO postgres2.public.table_name  (id,column_2,column_3,column_4,column_5)" +
                "VALUES(?,?,?,?,?)" + "ON CONFLICT (id) DO NOTHING";
        String sql2 = "SELECT *  FROM table_name where id = ?";

        ctDbPostgres = connectFromDb.connect("demo");


        PreparedStatement preparedStatement = ctDbPostgres.prepareStatement(sql2);

        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        ctDbPostgres2 = connectFromDb.connect("demo2");


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
            System.err.println("Ошибка " + ex.getMessage());
        }

    }


}





