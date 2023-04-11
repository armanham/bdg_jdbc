package com.bdg;

import com.bdg.db.DBConnectionManager;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        Connection connection = DBConnectionManager.getConnection();

        DBConnectionManager.closeConnection(connection);
    }
}