package com.bdg;

import com.bdg.db.DBConnectionManager;
import com.bdg.sql.DQL;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        Connection connection = DBConnectionManager.getConnection();

        DQL.showEmployee(connection);
        System.out.println(DQL.countRowsOfEmployee(connection));

        DBConnectionManager.closeConnection();
    }
}