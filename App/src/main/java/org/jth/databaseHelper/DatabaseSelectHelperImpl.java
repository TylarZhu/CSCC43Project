package org.jth.databaseHelper;

import java.sql.*;
import org.jth.databaseHelper.DatabaseDriver.*;

import static org.jth.databaseHelper.DatabaseDriver.*;

public class DatabaseSelectHelperImpl implements DatabaseSelectHelper {

  @Override
  public ResultSet selectAllListings() {
    try {
      Connection connection = connectingToDatabase();
      Statement statement = connection.createStatement();
      String sql = "SELECT * FROM listings;";
      ResultSet resultSet = statement.executeQuery(sql);
      connection.close();
      statement.close();
      return resultSet;
    } catch (Exception e) {
      System.out.println("Something went wrong with select all from listing! see below details: ");
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public ResultSet selectAllUnavailableTime() {
    try{
      Connection connection = connectingToDatabase();
      Statement statement = connection.createStatement();
      String sql = "SELECT * FROM unavailable_times;";
      ResultSet resultSet = statement.executeQuery(sql);
      connection.close();
      statement.close();
      return resultSet;
    }catch (Exception e) {
      System.out.println("Something went wrong with select all from unavailable time! see below details: ");
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public ResultSet selectListingIdsAndTimesByUnavailableTime() {
    try{
      Connection connection = connectingToDatabase();
      Statement statement = connection.createStatement();
      String sql = "SELECT listings.id, unavailable_times.times FROM unavailable_times " +
          "INNER JOIN listings ON unavailable_times.list_id = listings.id;";
      ResultSet resultSet = statement.executeQuery(sql);
      connection.close();
      statement.close();
      return resultSet;
    }catch (Exception e) {
      System.out.println("Something went wrong with select listing ids and times By unavailable time! see below details: ");
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public ResultSet selectListingsByPostalCode(String postalCode) {
    try {
      Connection connection = connectingToDatabase();
      String sql = "SELECT * FROM listings WHERE  postal_code = (?);";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, postalCode);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        System.out.println(resultSet.getString("address"));
      }
      preparedStatement.close();
      connection.close();
      return resultSet;
    } catch (Exception e) {
      System.out.println("Something went wrong with select Listings By Postal Code! see below details: ");
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public ResultSet selectListingsByLatitudeLongitude(double latitude, double longitude, double distance) {
    try {
      Connection connection = connectingToDatabase();
      String sql = "SELECT * From listings WHERE ((? - longitude < ?) AND (? - longitude >= 0)) AND ((? - latitude < ?) AND (? - latitude >= 0));";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setDouble(1, longitude);
      preparedStatement.setDouble(2, distance);
      preparedStatement.setDouble(3, longitude);
      preparedStatement.setDouble(4, latitude);
      preparedStatement.setDouble(5, distance);
      preparedStatement.setDouble(6, latitude);
      ResultSet resultSet = preparedStatement.executeQuery();
      while(resultSet.next()) {
        System.out.println(resultSet.getString("address"));
      }
      preparedStatement.close();
      connection.close();
      return resultSet;
    } catch (Exception e) {
      System.out.println("Something went wrong with select Listings By Latitude Longitude! see below details: ");
      e.printStackTrace();
      return null;
    }
  }
}
