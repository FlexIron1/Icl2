package com.example.methods;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class Result {
    Result(ResultSet resultSet, PreparedStatement ps) throws SQLException {
        while (resultSet.next()) {
            ps.setInt(1, resultSet.getInt(1));
            ps.setInt(2, resultSet.getInt(2));
            ps.setInt(3, resultSet.getInt(3));
            ps.setInt(4, resultSet.getInt(4));
            ps.setInt(5, resultSet.getInt(5));
            ps.execute();
        }
    }

}
