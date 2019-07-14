package org.jth.databaseHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.jth.databaseHelper.DatabaseDriver.*;
import org.jth.fields.Amenities;
import org.jth.fields.ListingType;
import org.jth.listings.Listings;

import static org.jth.databaseHelper.DatabaseDriver.*;

public class DatabaseSelectHelperImpl implements DatabaseSelectHelper {

  private ArrayList<Listings> listings = new ArrayList<>();

  private void loadFromDB(ResultSet resultSet){
    try {
      while(resultSet.next()) {
        listings.add(new Listings(resultSet.getInt("id"),
            resultSet.getDouble("latitude"),
            resultSet.getDouble("longitude"),
            resultSet.getString("address"),
            resultSet.getString("postal_code"),
            ListingType.valueOf(resultSet.getString("list_type")),
            resultSet.getDouble("price"),
            Amenities.valueOf(resultSet.getString("amenities")),
            resultSet.getString("city"),
            resultSet.getString("country")));
      }
      resultSet.close();
    } catch (Exception e) {
      System.out.println("Something went wrong with load from DB! see below details: ");
      e.printStackTrace();
    }
  }

  public ArrayList<Listings> getListings(){
    return listings;
  }

  @Override
  public void selectAllListings() {
    try {
      Connection connection = connectingToDatabase();
      Statement statement = connection.createStatement();
      String sql = "SELECT * FROM listings;";
      ResultSet resultSet = statement.executeQuery(sql);
      loadFromDB(resultSet);
      connection.close();
      statement.close();
    } catch (Exception e) {
      System.out.println("Something went wrong with select all from listing! see below details: ");
      e.printStackTrace();
    }
  }

  @Override
  public void selectAllUnavailableTime() {
    try{
      Connection connection = connectingToDatabase();
      Statement statement = connection.createStatement();
      String sql = "SELECT * FROM unavailable_times;";
      ResultSet resultSet = statement.executeQuery(sql);
      loadFromDB(resultSet);
      connection.close();
      statement.close();
    }catch (Exception e) {
      System.out.println("Something went wrong with select all from unavailable time! see below details: ");
      e.printStackTrace();
    }
  }

  @Override
  public void selectListingIdsAndTimesByUnavailableTime() {
    try{
      Connection connection = connectingToDatabase();
      Statement statement = connection.createStatement();
      String sql = "SELECT * FROM unavailable_times " +
          "INNER JOIN listings ON unavailable_times.list_id = listings.id;";
      ResultSet resultSet = statement.executeQuery(sql);
      loadFromDB(resultSet);
      connection.close();
      statement.close();
    }catch (Exception e) {
      System.out.println("Something went wrong with select listing ids and times By unavailable time! see below details: ");
      e.printStackTrace();
    }
  }

  @Override
  public void selectListingsByPostalCode(String postalCode) {
    try {
      Connection connection = connectingToDatabase();
      String sql = "SELECT * FROM listings WHERE  postal_code = (?);";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, postalCode);
      ResultSet resultSet = preparedStatement.executeQuery();
      loadFromDB(resultSet);
      preparedStatement.close();
      connection.close();
    } catch (Exception e) {
      System.out.println("Something went wrong with select Listings By Postal Code! see below details: ");
      e.printStackTrace();
    }
  }

  @Override
  public void selectListingsByLatitudeLongitude(double latitude, double longitude, double distance) {
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
      loadFromDB(resultSet);
      preparedStatement.close();
      connection.close();
    } catch (Exception e) {
      System.out.println("Something went wrong with select Listings By Latitude Longitude! see below details: ");
      e.printStackTrace();
    }
  }
}
