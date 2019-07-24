package org.jth.databaseHelper;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.jth.fields.Amenities;
import org.jth.fields.ListingType;
import org.jth.listings.Listings;
import org.jth.listings.UnavailableTime;
import org.jth.user.Hosts;
import org.jth.user.Renters;
import org.jth.user.Users;

import static org.jth.databaseHelper.DatabaseDriver.*;

public class DatabaseSelectHelperImpl implements DatabaseSelectHelper {

  private ArrayList<Listings> listings = new ArrayList<>();
  private ArrayList<UnavailableTime> unavailableTimes = new ArrayList<>();
  private ArrayList<Users> users= new ArrayList<>();

  private void loadUnavailableTimesFromDB(ResultSet resultSet) {
    try {
      while(resultSet.next()) {
        unavailableTimes.add(new UnavailableTime(resultSet.getInt("id"),
            resultSet.getInt("list_id"),
            parseStringToDate(resultSet.getString("times"))));
      }
      resultSet.close();
    } catch (Exception e) {
      System.out.println("Something went wrong with load unavailable Times from DB! see below details: ");
      e.printStackTrace();
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

  private void loadRenterFromDB(ResultSet resultSet) {
    try {
      while (resultSet.next()) {
        users.add(new Renters(resultSet.getInt("social_insurance_number"),
            resultSet.getString("first_name"),
            resultSet.getString("last_name"),
            resultSet.getString("address"),
            resultSet.getString("postal_code"),
            parseStringToDate(resultSet.getString("date_of_birth")),
            resultSet.getString("occupation"),
            resultSet.getInt("renter_id"),
            resultSet.getString("card_number"),
            resultSet.getString("card_expiry_date"),
            resultSet.getInt("cvv")));
      }
      resultSet.close();
    } catch (Exception e) {
      System.out.println("Something went wrong with load renter from DB! see below details: ");
      e.printStackTrace();
    }
  }

  private void loadHostsFromDB(ResultSet hostInfo, Connection connection) {
    try {
      while (hostInfo.next()) {
        Hosts hosts = new Hosts(hostInfo.getInt("social_insurance_number"),
            hostInfo.getString("first_name"),
            hostInfo.getString("last_name"),
            hostInfo.getString("address"),
            hostInfo.getString("postal_code"),
            parseStringToDate(hostInfo.getString("date_of_birth")),
            hostInfo.getString("occupation"),
            hostInfo.getInt("host_id"));
        String sql = "SELECT list_id FROM hostOwnListings WHERE host_ins = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, hostInfo.getInt("host_profile"));
        ResultSet hostListings = preparedStatement.executeQuery();
        ArrayList<Integer> ownListings = new ArrayList<>();
        while (hostListings.next()) {
          ownListings.add(hostListings.getInt("list_id"));
        }
        hosts.setOwnListings(ownListings);
        users.add(hosts);
        hostListings.close();
      }
      hostInfo.close();

    } catch (Exception e) {
      System.out.println("Something went wrong with load host from DB! see below details: ");
      e.printStackTrace();
    }
  }

  private static Date parseStringToDate(String date) {
    try {
      return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    } catch (ParseException e) {
      System.out.println("Parse date error! see below details: ");
      e.printStackTrace();
      return null;
    }
  }

  private static String parseDatetoString(Date date) {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    return dateFormat.format(date);
  }

  public ArrayList<Listings> getListings(){
    return listings;
  }

  public ArrayList<UnavailableTime> getUnavailableTimes(){
    return unavailableTimes;
  }

  public ArrayList<Users> getUsers(){
    return users;
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
  public void selectListingIdsAndTimesByUnavailableTime(Date date) {
    try{
      Connection connection = connectingToDatabase();
      String sql = "SELECT * FROM unavailable_times WHERE times = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      System.out.println(parseDatetoString(date));
      preparedStatement.setString(1, parseDatetoString(date));
      ResultSet resultSet = preparedStatement.executeQuery();
      loadUnavailableTimesFromDB(resultSet);
      connection.close();
      preparedStatement.close();
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

  @Override
  public void selectAllUsers(int choice) {
    try {
      Connection connection = connectingToDatabase();
      String sql = null;
      if(choice == 1) {
        sql = "SELECT * FROM users NATURAL JOIN renters WHERE renters.renter_profile = users.social_insurance_number;";
      } else {
        sql = "SELECT * FROM users NATURAL JOIN hosts WHERE hosts.host_profile = users.social_insurance_number;";
      }
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sql);
      if(choice == 1) {
        loadRenterFromDB(resultSet);
      } else {
        loadHostsFromDB(resultSet, connection);
      }
      connection.close();
      statement.close();
    } catch (Exception e) {
      System.out.println("Something went wrong with select All Renters! see below details: ");
      e.printStackTrace();
    }
  }
}