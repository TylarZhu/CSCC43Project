package org.jth.databaseHelper;

import org.jth.Fields.*;
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
    } catch (Exception e) {
      System.out.println("Something went wrong with insert listing! see below details: ");
      e.printStackTrace();
    }
  }

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
    } catch (Exception e) {
      System.out.println("Something went wrong with insert Unavailable Times! see below details: ");
      e.printStackTrace();
    }
  }

  private static String parseDate(Date date) {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
    return dateFormat.format(date);
  }
}
