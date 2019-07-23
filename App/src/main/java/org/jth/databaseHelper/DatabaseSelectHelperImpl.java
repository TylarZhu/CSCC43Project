package org.jth.databaseHelper;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jth.databaseHelper.DatabaseDriver.*;
import org.jth.fields.Amenities;
import org.jth.fields.ListingType;
import org.jth.listings.Listings;
import org.jth.listings.UnavailableTime;

import static org.jth.databaseHelper.DatabaseDriver.*;

public class DatabaseSelectHelperImpl implements DatabaseSelectHelper {

  private ArrayList<Listings> listings = new ArrayList<>();
  private ArrayList<UnavailableTime> unavailableTimes = new ArrayList<>();

  public static void main(String[] args) {
    DatabaseSelectHelper databaseSelectHelper = new DatabaseSelectHelperImpl();

  }

  private void loadUnavailableTimesFromDB(ResultSet resultSet) {
    try {
      while(resultSet.next()) {
        unavailableTimes.add(new UnavailableTime(resultSet.getInt("id"),
            resultSet.getInt("list_id"),
            parseDate(resultSet.getString("times"))));
      }
      resultSet.close();
    } catch (Exception e) {
      System.out.println("Something went wrong with load unavailable Times from DB! see below details: ");
      e.printStackTrace();
    }
  }

  public static Date parseDate(String date) {
    try {
      return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    } catch (ParseException e) {
      System.out.println("Parse date error! see below details: ");
      e.printStackTrace();
      return null;
    }
  }

  private void loadListingsFromDB(ResultSet resultSet){
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
      System.out.println("Something went wrong with load listings from DB! see below details: ");
      e.printStackTrace();
    }
  }

  public ArrayList<Listings> getListings(){
    return listings;
  }

  public ArrayList<UnavailableTime> getUnavailableTimes(){
    return unavailableTimes;
  }

  @Override
  public void selectAllListings(int choice) {
    try {
      Connection connection = connectingToDatabase();
      Statement statement = connection.createStatement();
      String sql = null;
      if(choice == 1) {
        sql = "SELECT * FROM listings ORDER BY price DESC;";
      } else {
        sql = "SELECT * FROM listings ORDER BY price ASC;";
      }
      ResultSet resultSet = statement.executeQuery(sql);
      loadListingsFromDB(resultSet);
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
      loadUnavailableTimesFromDB(resultSet);
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
      loadUnavailableTimesFromDB(resultSet);
      connection.close();
      statement.close();
    }catch (Exception e) {
      System.out.println("Something went wrong with select listing ids and times By unavailable time! see below details: ");
      e.printStackTrace();
    }
  }

  @Override
  public void selectListingsByPostalCode(String postalCode, int choice) {
    try {
      Connection connection = connectingToDatabase();
      String sql = null;
      if(choice == 1) {
        sql = "SELECT * FROM listings WHERE postal_code = (?) ORDER BY price DESC;";
      } else {
        sql = "SELECT * FROM listings WHERE postal_code = (?) ORDER BY price ASC;";
      }
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, postalCode);
      ResultSet resultSet = preparedStatement.executeQuery();
      loadListingsFromDB(resultSet);
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
      String sql = "SELECT * From listings WHERE (SQRT(POWER((? - longitude), 2) + POWER((? - latitude), 2)) <= ?) " +
          "ORDER BY (SQRT(POWER((? - longitude), 2) + POWER((? - latitude), 2)));";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setDouble(1, longitude);
      preparedStatement.setDouble(2, latitude);
      preparedStatement.setDouble(3, distance);
      preparedStatement.setDouble(4, longitude);
      preparedStatement.setDouble(5, latitude);
      ResultSet resultSet = preparedStatement.executeQuery();
      loadListingsFromDB(resultSet);
      preparedStatement.close();
      connection.close();
    } catch (Exception e) {
      System.out.println("Something went wrong with select Listings By Latitude Longitude! see below details: ");
      e.printStackTrace();
    }
  }

  @Override
  public void selectListingsByAddress(String address, int choice) {
    try {
      Connection connection = connectingToDatabase();
      String sql = null;
      if(choice == 1) {
        sql = "SELECT * FROM listings WHERE address = (?) ORDER BY price DESC;";
      } else {
        sql = "SELECT * FROM listings WHERE address = (?) ORDER BY price ASC;";
      }
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, address);
      ResultSet resultSet = preparedStatement.executeQuery();
      loadListingsFromDB(resultSet);
      preparedStatement.close();
      connection.close();
    } catch (Exception e) {
      System.out.println("Something went wrong with select Listings By Address! see below details: ");
      e.printStackTrace();
    }
  }


}