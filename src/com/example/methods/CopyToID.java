package com.example.methods;


import com.example.connect.ConnectOne;


import java.io.IOException;
import java.sql.*;

public class CopyToID {

    public void id(int id) throws IOException, SQLException {

        ConnectOne connectOne = new ConnectOne();


        Connection connection;
        Connection connection1;

        connection = connectOne.connect("demo");

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT *  FROM table_name where id = ?");

        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        connection1 = connectOne.connect("demo2");


        String sql2 = "INSERT INTO postgres2.public.table_name  (id,column_2,column_3,column_4,column_5)" +
                "VALUES(?,?,?,?,?)" + "ON CONFLICT (id) DO NOTHING";

        PreparedStatement ps = connection1.prepareStatement(sql2);

        while (resultSet.next()) {
            ps.setInt(1, resultSet.getInt(1));
            ps.setInt(2, resultSet.getInt(2));
            ps.setInt(3, resultSet.getInt(3));
            ps.setInt(4, resultSet.getInt(4));
            ps.setInt(5, resultSet.getInt(5));
            ps.execute();
        }

    }

    public static void main(String[] args) throws IOException, SQLException {
        CopyToID copyToID = new CopyToID();
        copyToID.id(3);
    }
}



