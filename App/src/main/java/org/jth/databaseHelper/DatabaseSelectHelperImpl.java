package org.jth.databaseHelper;

import java.sql.*;
import org.jth.databaseHelper.DatabaseDriver.*;

import static org.jth.databaseHelper.DatabaseDriver.*;

public class DatabaseSelectHelperImpl implements DatabaseSelectHelper {

  @Override
  public ResultSet selectAllItemsFromListings() {
    try {
      Connection connection = connectingToDatabase();
      Statement statement = connection.createStatement();
      String sql = "SELECT * FROM listings;";
      ResultSet resultSet = statement.executeQuery(sql);
      while (resultSet.next()) {
        double latitude = resultSet.getDouble("latitude");
        double longitude = resultSet.getDouble("longitude");
        String address = resultSet.getString("address");
        double price = resultSet.getDouble("price");
        System.out.println("latitude: " + latitude);
        System.out.println("longitude: " + longitude);
        System.out.println("address: " + address);
        System.out.println("price: " + price);
      }
      return resultSet;
    } catch (Exception e) {
      System.out.println("Something went wrong with select all items from listing! see below details: ");
      e.printStackTrace();
      return null;
    }
  }
}
