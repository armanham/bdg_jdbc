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

    public static int insertIntoEmployee(Connection con,
                                             String name,
                                             String gender,
                                             String position,
                                             String department,
                                             String office,
                                             int age,
                                             LocalDate startDate,
                                             java.math.BigDecimal salary
    ) {
        checkNullConnection(con);

        if (name == null || name.isEmpty() ||
                position == null || position.isEmpty() ||
                department == null || department.isEmpty() ||
                office == null || office.isEmpty() ||
                age <= 0 || age >= 120 ||
                salary.intValue() <= 0
        ) {
            throw new IllegalArgumentException("Passed wrong valur for one of the parameters.");
        }
        if (gender == null || (!gender.equals("Male") & !gender.equals("Female"))) {
            throw new IllegalArgumentException("Passed wrong value for parameter 'gender'.");
        }

        int result = 0;

        try {
            pst = con.prepareStatement("INSERT INTO employee(" +
                    "name, gender, position, department, office, age, start_date, salary) " +
                    "values(?, ?, ? , ?, ?, ?, ?, ?)");

            pst.setString(1, name);
            pst.setString(2, gender);
            pst.setString(3, position);
            pst.setString(4, department);
            pst.setString(5, office);
            pst.setInt(6, age);
            pst.setDate(7, Date.valueOf(startDate));
            pst.setBigDecimal(8, salary);

            result = pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                pst.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }


    private static void checkNullConnection(Connection con) {
        if (con == null) {
            throw new NullPointerException("Passed null value:");
        }
    }
}