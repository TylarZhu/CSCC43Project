package org.jth.databaseHelper;

import java.sql.*;

import org.jth.Fields.Amenities;
import org.jth.Fields.ListingType;
import org.jth.databaseHelper.DatabaseInsertHelper;


public class DatabaseDriver {
    private static final String dbClassName = "com.mysql.cj.jdbc.Driver";
    private static final String CONNECTION = "jdbc:mysql://localhost:3306/CSCC43Pro" +
            "?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "zx961130";



    public static void main(String[] args) {
        Connection connection = connectingToDatabase();
        initializeDatabase(connection);
        DatabaseInsertHelperImpl databaseInsertHelper = new DatabaseInsertHelperImpl();
        DatabaseSelectHelperImpl databaseSelectHelper = new DatabaseSelectHelperImpl();
        databaseInsertHelper.insertListings(12,12,"70 TOWN CENTER", "M1P 0B2",
            ListingType.APARTMENT, 1000000, Amenities.AIR_CONDITIONING);
        databaseInsertHelper.insertListings(12,13,"80 TOWN CENTER", "M1P 0B1",
            ListingType.BED_AND_BREAKFAST, 3500000, Amenities.AIR_CONDITIONING);
        databaseInsertHelper.insertListings(12,14,"50 TOWN CENTER", "M1P 0B0",
            ListingType.BUNGALOW, 6700000, Amenities.AIR_CONDITIONING);
        databaseSelectHelper.selectAllItemsFromListings();

        try{
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
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
            System.out.println("Connect successed!");
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
                "PRIMARY KEY(id)" +
                ");";
            statement.executeUpdate(sql);

            sql = "CREATE TABLE unavailable_times(" +
                "date_id INT AUTO_INCREMENT," +
                "list_id INT," +
                "time DATE NOT NULL," +
                "PRIMARY KEY (date_id)," +
                "FOREIGN KEY (list_id) REFERENCES listings (id)" +
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
