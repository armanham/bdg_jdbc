package com.bdg.sql;

import java.sql.*;
import java.time.LocalDate;

public class DQL {

    private static Statement st;
    private static PreparedStatement pst;
    private static CallableStatement cst;
    private static ResultSet rs;


    public static int countRowsOfEmployee(Connection con) {
        checkNullConnection(con);

        try {
            pst = con.prepareStatement("select count(*) from employee");
            rs = pst.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                assert pst != null;
                assert rs != null;
                pst.close();
                rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return -1;
    }


    public static void showEmployee(Connection con) {
        checkNullConnection(con);

        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from employee");

            ResultSetMetaData rsmd = rs.getMetaData();

            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                assert st != null;
                assert rs != null;
                st.close();
                rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

//    public static boolean insertIntoEmployee(Connection con,
//                                             String name,
//                                             String gender,
//                                             String position,
//                                             String department,
//                                             String office,
//                                             int age,
//                                             LocalDate startDate,
//                                             double salary
//    ) {
//        checkNullConnection(con);
//
//        if (name == null || name.isEmpty() ||
//                gender == null || !gender.equals("Male") || !gender.equals("Female") ||
//                position == null || position.isEmpty() ||
//                department == null || department.isEmpty() ||
//                office == null || office.isEmpty() ||
//                age <= 0 || age >= 120 ||
//                salary <= 0
//        ) {
//            throw new IllegalArgumentException("Passed wrong valur for one of the parameters.");
//        }
//
//        try {
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        } finally {
//
//        }
//    }


    private static void checkNullConnection(Connection con) {
        if (con == null) {
            throw new NullPointerException("Passed null value:");
        }
    }
}