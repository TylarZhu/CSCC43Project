package org.jth.databaseHelper;

import java.sql.*;


public class DatabaseDriver {
    private static final String dbClassName = "com.mysql.cj.jdbc.Driver";
    private static final String CONNECTION = "jdbc:mysql://localhost:3306/CSCC43Pro" +
        "?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "aax020020";


    /**
     * Connects to the database if it is already set up.
     *
     * @return Connection to the database
     */
    public static Connection connectingToDatabase() {
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

    public static Connection initializeDatabase(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS listings (" +
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

            sql = "CREATE TABLE IF NOT EXISTS unavailable_times(" +
                "id INT AUTO_INCREMENT," +
                "list_id INT," +
                "times TEXT NOT NULL," +
                "PRIMARY KEY (id)," +
                "FOREIGN KEY (list_id) REFERENCES listings(id)" +
                ");";

            statement.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS users(" +
                "social_insurance_number INT," +
                "first_name TEXT NOT NULL," +
                "last_name TEXT NOT NULL," +
                "address TEXT NOT NULL," +
                "postal_code TEXT NOT NULL," +
                "date_of_birth TEXT NOT NULL," +
                "occupation TEXT," +
                "PRIMARY KEY (social_insurance_number)" +
                ");";
            statement.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS renters(" +
                "renter_id INT AUTO_INCREMENT," +
                "renter_profile INT," +
                "card_number TEXT NOT NULL," +
                "card_expiry_date TEXT NOT NULL," +
                "cvv INT NOT NULL," +
                "PRIMARY KEY (renter_id)," +
                "FOREIGN KEY (renter_profile) REFERENCES users(social_insurance_number)" +
                ");";
            statement.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS hosts(" +
                "host_id INT AUTO_INCREMENT," +
                "host_profile INT," +
                "PRIMARY KEY (host_id)," +
                "FOREIGN KEY (host_profile) REFERENCES users(social_insurance_number)" +
                ");";
            statement.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS commentTable(" +
                "comment_id INT AUTO_INCREMENT," +
                "fromUsr INT," +
                "toUsr INT," +
                "content TEXT NOT NULL," +
                "rate INT NOT NULL," +
                "PRIMARY KEY (comment_id)," +
                "FOREIGN KEY (fromUsr) REFERENCES users(social_insurance_number)," +
                "FOREIGN KEY (toUsr) REFERENCES users(social_insurance_number)" +
                ");";
            statement.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS relationshipRenterHost(" +
                "relation_id INT AUTO_INCREMENT," +
                "renter_ins INT," +
                "host_ins INT," +
                "list_id INT," +
                "PRIMARY KEY (relation_id)," +
                "FOREIGN KEY (renter_ins) REFERENCES users(social_insurance_number)," +
                "FOREIGN KEY (host_ins) REFERENCES users(social_insurance_number)," +
                "FOREIGN KEY (list_id) REFERENCES listings(id)" +
                ");";
            statement.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS hostOwnListings(" +
                "id INT AUTO_INCREMENT," +
                "list_id INT," +
                "host_ins INT," +
                "PRIMARY KEY (id)," +
                "FOREIGN KEY (list_id) REFERENCES listings(id)," +
                "FOREIGN KEY (host_ins) REFERENCES users(social_insurance_number)" +
                ");";
            statement.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS futureBooking(" +
                    "id INT AUTO_INCREMENT," +
                    "renter_ins INT," +
                    "list_id INT," +
                    "PRIMARY KEY (id)," +
                    "FOREIGN KEY (list_id) REFERENCES listings(id)," +
                    "FOREIGN KEY (renter_ins) REFERENCES users(social_insurance_number)" +
                    ");";

            statement.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS rentalHistory(" +
                    "id INT AUTO_INCREMENT," +
                    "host_ins INT," +
                    "list_id INT," +
                    "PRIMARY KEY (id)," +
                    "FOREIGN KEY (host_ins) REFERENCES users(social_insurance_number)," +
                    "FOREIGN KEY (list_id) REFERENCES listings(id)" +
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

            sql = "DROP TABLE hostOwnListings;";
            statement.executeUpdate(sql);

            sql = "DROP TABLE rentalHistory;";
            statement.executeUpdate(sql);

            sql = "DROP TABLE futureBooking;";
            statement.executeUpdate(sql);

            sql = "DROP TABLE renters;";
            statement.executeUpdate(sql);

            sql = "DROP TABLE hosts;";
            statement.executeUpdate(sql);

            sql = "DROP TABLE relationshipRenterHost";
            statement.executeUpdate(sql);

            sql = "DROP TABLE commentTable;";
            statement.executeUpdate(sql);

            sql = "DROP TABLE users;";
            statement.executeUpdate(sql);

            sql = "DROP TABLE listings;";
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

//    public static void main(String[] args) {
//        Connection connection = connectingToDatabase();
//        dropDatabase(connection);
//    }
}
