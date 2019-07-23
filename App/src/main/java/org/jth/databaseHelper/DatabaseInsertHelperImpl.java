package org.jth.databaseHelper;

import org.jth.fields.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.jth.databaseHelper.DatabaseDriver.*;


public class DatabaseInsertHelperImpl implements DatabaseInsertHelper {



  @Override
  public void insertListings(double latitude, double longitude, String address, String postal_code, ListingType listingType, double price, Amenities amenities, String city, String country) {
    try {
      Connection connection = connectingToDatabase();
      String sql = "INSERT INTO listings (latitude, longitude, address, postal_code, list_type, price, amenities, city, country)" +
          "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setDouble(1, latitude);
      preparedStatement.setDouble(2, longitude);
      preparedStatement.setString(3, address);
      preparedStatement.setString(4, postal_code);
      preparedStatement.setString(5, listingType.name());
      preparedStatement.setDouble(6, price);
      preparedStatement.setString(7,amenities.name());
      preparedStatement.setString(8,city);
      preparedStatement.setString(9,country);
      preparedStatement.executeUpdate();
      preparedStatement.close();
      connection.close();
    } catch (Exception e) {
      System.out.println("Something went wrong with insert listing! see below details: ");
      e.printStackTrace();
    }
  }

  private void insertUsers(int ins, String firstName, String lastName, String address, String postal_code, Date date_of_birth, String occupation){
    try {
      Connection connection = connectingToDatabase();
      String sql = "INSERT INTO Users VALUES (?,?,?,?,?,?,?);";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, ins);
      preparedStatement.setString(2, firstName);
      preparedStatement.setString(3, lastName);
      preparedStatement.setString(4, address);
      preparedStatement.setString(5, postal_code);
      preparedStatement.setString(6, parseDate(date_of_birth));
      preparedStatement.setString(7, occupation);
      preparedStatement.executeUpdate();
      preparedStatement.close();
      connection.close();
    }catch (Exception e) {
      System.out.println("Something went wrong with insert User! see below details: ");
      e.printStackTrace();
    }
  }

  @Override
  public void insertRentors(int ins, String firstName, String lastName, String address, String postal_code,
                            Date date_of_birth, String occupation, String cardNumnber, String card_expiry_date, int cvv) {
    insertUsers(ins, firstName, lastName, address, postal_code, date_of_birth, occupation);
    try {
      Connection connection = connectingToDatabase();
      String sql = "INSERT renters (renter_profile, card_number, card_expiry_date, cvv) VALUES (?, ?, ?, ?);";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, ins);
      preparedStatement.setString(2, cardNumnber);
      preparedStatement.setString(3, card_expiry_date);
      preparedStatement.setInt(4, cvv);
      preparedStatement.executeUpdate();
      preparedStatement.close();
      connection.close();
    } catch (Exception e) {
      System.out.println("Something went wrong with insert rentors! see below details: ");
      e.printStackTrace();
    }
  }

  @Override
  public void insertHosts(int ins, String firstName, String lastName, String address, String postal_code, Date date_of_birth, String occupation) {
    insertUsers(ins, firstName, lastName, address, postal_code, date_of_birth, occupation);
    try {
      Connection connection = connectingToDatabase();
      String sql = "INSERT hosts (host_profile) VALUES (?);";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, ins);
      preparedStatement.executeUpdate();
      preparedStatement.close();
      connection.close();
    } catch (Exception e) {
      System.out.println("Something went wrong with insert rentors! see below details: ");
      e.printStackTrace();
    }
  }

  public void insertHostList(){}

  @Override
  public void insertUnavailableTimes(int list_id, Date date) {
    try {
      Connection connection = connectingToDatabase();
      String sql = "INSERT INTO unavailable_times (list_id, times) VALUES (?, ?);";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setInt(1, list_id);
      preparedStatement.setString(2, parseDate(date));
      preparedStatement.executeUpdate();
      preparedStatement.close();
      connection.close();
    } catch (Exception e) {
      System.out.println("Something went wrong with insert Unavailable Times! see below details: ");
      e.printStackTrace();
    }
  }

  private static String parseDate(Date date) {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    return dateFormat.format(date);
  }
}
