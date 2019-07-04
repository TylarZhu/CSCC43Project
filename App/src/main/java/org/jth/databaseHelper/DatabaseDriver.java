package org.jth.databaseHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseDriver {
    private static final String dbClassName = "com.mysql.cj.jdbc.Driver";
    private static final String CONNECTION = "jdbc:mysql://localhost:3306/CSCC43Pro" +
            "?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "aax020020";



    public static void main(String[] args) {
        Connection connection = connectingToDatabase();
    }


    /**
     * Connects to the database if it is already set up.
     * @return Connection to the database
     */
    public static Connection connectingToDatabase() {
        System.out.println("Connecting to database...");
        Connection connection = null;
        try {
            Class.forName(dbClassName);
            connection = DriverManager.getConnection(CONNECTION, USER, PASSWORD);
            System.out.println("Connect successed!");
        } catch (Exception e) {
            System.out.println("Something went wrong with your connection! see below details: ");
            e.printStackTrace();
        }

        return connection;
    }
}
