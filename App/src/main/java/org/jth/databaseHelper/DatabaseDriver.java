package org.jth.databaseHelper;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DatabaseDriver {
    private static final String dbClassName = "com.mysql.cj.jdbc.Driver";
    private static final String CONNECTION = "jdbc:mysql://localhost:3306/CSCC43Pro" +
            "?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "aax020020";


    private static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-mm-dd").parse(date);
        } catch (ParseException e) {
            System.out.println("Parse date error! see below details: ");
            e.printStackTrace();
            return null;
        }
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
        } catch (Exception e) {
            System.out.println("Something went wrong with your connection! see below details: ");
            e.printStackTrace();
        }

        return connection;
    }

    private static Connection initializeDatabase(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE listings (" +
                "id INT AUTO_INCREMENT," +
                "latitude DOUBLE NOT NULL," +
                "longitude DOUBLE NOT NULL," +
                "address TEXT NOT NULL," +
                "postal_code TEXT NOT NULL," +
                "list_type TEXT NOT NULL," +
                "price DOUBLE NOT NULL," +
                "amenities TEXT NOT NULL," +
                "city TEXT NOT NULL," +
                "country TEXT NOT NULL," +
                "PRIMARY KEY(id)" +
                ");";
            statement.executeUpdate(sql);

            sql = "CREATE TABLE unavailable_times(" +
                "date_id INT AUTO_INCREMENT," +
                "list_id INT," +
                "times TEXT NOT NULL," +
                "PRIMARY KEY (date_id)" +
                ");";

            statement.executeUpdate(sql);

            sql = "CREATE TABLE users(" +
                "social_insurance_number INT PRIMARY KEY," +
                "address TEXT NOT NULL," +
                "postal_code TEXT NOT NULL," +
                "date_of_birth DATE NOT NULL," +
                "occupation TEXT" +
                ");";
            statement.executeUpdate(sql);
            statement.close();
            System.out.println("Initialize database success!");
            return connection;
        } catch (Exception e) {
            System.out.println("Something went wrong with initialize database! see below details: ");
            e.printStackTrace();
            return null;
        }
    }

    private static Connection dropDatabase(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            statement = connection.createStatement();

            String sql = "DROP TABLE unavailable_times;";
            statement.executeUpdate(sql);

            sql = "DROP TABLE listings;";
            statement.executeUpdate(sql);

            sql = "DROP TABLE users;";
            statement.executeUpdate(sql);

            statement.close();
            System.out.println("Drop database success!");
            return connection;
        } catch (Exception e) {
            System.out.println("Something went wrong with drop database! see below details: ");
            e.printStackTrace();
            return null;
        }
    }
}
