package org.jth.databaseHelper;

import java.sql.*;
import org.jth.databaseHelper.DatabaseDriver.*;

import static org.jth.databaseHelper.DatabaseDriver.*;

public class DatabaseSelectHelperImpl implements DatabaseSelectHelper {

  @Override
  public ResultSet selectAllFromListings() {
    try {
      Connection connection = connectingToDatabase();
      Statement statement = connection.createStatement();
      String sql = "SELECT * FROM listings;";
      ResultSet resultSet = statement.executeQuery(sql);
      return resultSet;
    } catch (Exception e) {
      System.out.println("Something went wrong with select all from listing! see below details: ");
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public ResultSet selectAllFromUnavailableTime() {
    try{
      Connection connection = connectingToDatabase();
      Statement statement = connection.createStatement();
      String sql = "SELECT * FROM unavailable_times;";
      ResultSet resultSet = statement.executeQuery(sql);
      while(resultSet.next()) {
        System.out.println(resultSet.getInt("date_id"));
        System.out.println(resultSet.getString("times"));
      }
      return resultSet;
    }catch (Exception e) {
      System.out.println("Something went wrong with select all from unavailable time! see below details: ");
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public ResultSet selectAllUnavailableListingsIds() {
    try{
      Connection connection = connectingToDatabase();
      Statement statement = connection.createStatement();
      String sql = "SELECT listings.id, unavailable_times.times FROM unavailable_times " +
          "INNER JOIN listings ON unavailable_times.list_id = listings.id;";
      ResultSet resultSet = statement.executeQuery(sql);
      return resultSet;
    }catch (Exception e) {
      System.out.println("Something went wrong with select unavailable listings from listing! see below details: ");
      e.printStackTrace();
      return null;
    }
  }
}
