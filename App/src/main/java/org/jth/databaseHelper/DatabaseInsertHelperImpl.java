package org.jth.databaseHelper;

import org.jth.Fields.*;
import java.sql.*;
import static org.jth.databaseHelper.DatabaseDriver.*;


public class DatabaseInsertHelperImpl implements DatabaseInsertHelper {

  @Override
  public void insertListings(double latitude, double longitude, String address, String postal_code, ListingType listingType, double price, Amenities amenities) {
    try {
      Connection connection = connectingToDatabase();
      String sql = "INSERT INTO listings (latitude, longitude, address, postal_code, list_type, price, amenities)" +
          "VALUES (?, ?, ?, ?, ?, ?, ?);";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setDouble(1, latitude);
      preparedStatement.setDouble(2, longitude);
      preparedStatement.setString(3, address);
      preparedStatement.setString(4, postal_code);
      preparedStatement.setString(5, listingType.name());
      preparedStatement.setDouble(6, price);
      preparedStatement.setString(7,amenities.name());
      preparedStatement.executeUpdate();
    } catch (Exception e) {
      System.out.println("Something went wrong with insert listing! see below details: ");
      e.printStackTrace();
    }
  }
  public void insertUsers(int ins, String address, String postal_code, Time date_of_birth, String occupation ){
    try {
      Connection connection = connectingToDatabase();
      String sql = "INSERT INTO Users(ins, address, postal_code, date_of_birth, occupation)" + "VALUES(?,?,?,?,?);";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, ins);
      preparedStatement.setString(2, address);
      preparedStatement.setString(3, postal_code);
      preparedStatement.setTime(4, date_of_birth);
      preparedStatement.setString(5, occupation);


    }catch (Exception e) {
      System.out.println("Something went wrong with insert User! see below details: ");
      e.printStackTrace();
    }
  }
}
