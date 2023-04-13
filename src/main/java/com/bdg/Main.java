package com.bdg;

import com.bdg.db.DBConnectionManager;
import com.bdg.sql.DQL;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.Month;

public class Main {
    public static void main(String[] args) throws SQLException {

        Connection connection = DBConnectionManager.getConnection();

        DQL.showEmployee(connection);
        System.out.println(DQL.countRowsOfEmployee(connection));
        DQL.insertIntoEmployee(
                connection,
                "Arman",
                "Male",
                "Developer",
                "SoftEng",
                "BDG",
                21,
                LocalDate.of(2002, Month.APRIL, 22),
                new BigDecimal("200.000")
                );

        DBConnectionManager.closeConnection();
    }
}