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
import org.jth.user.Hosts;
import org.jth.user.Renters;
import org.jth.user.Users;

import static org.jth.databaseHelper.DatabaseDriver.*;

public class DatabaseSelectHelperImpl implements DatabaseSelectHelper {

  private ArrayList<Listings> listings = new ArrayList<>();
  private ArrayList<Users> users = new ArrayList<>();

  private void loadListingsFromDB(ResultSet resultSet, Connection connection){
    try {
      while(resultSet.next()) {
        Listings listing = new Listings(resultSet.getInt("id"),
            resultSet.getDouble("latitude"),
            resultSet.getDouble("longitude"),
            resultSet.getString("address"),
            resultSet.getString("postal_code"),
            ListingType.valueOf(resultSet.getString("list_type")),
            resultSet.getDouble("price"),
            resultSet.getString("city"),
            resultSet.getString("country"),
            resultSet.getBoolean("availability"));

        String sql = "SELECT * FROM unavailable_times WHERE list_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, resultSet.getInt("id"));
        ResultSet listingsUnavailableTime = preparedStatement.executeQuery();
        while (listingsUnavailableTime.next()) {
          listing.setUnavailableTime(parseStringToDate(listingsUnavailableTime.getString("fromTime")),
              parseStringToDate(listingsUnavailableTime.getString("toTime")));
        }
        listingsUnavailableTime.close();

        sql = "SELECT * FROM listingsAmenities WHERE list_id = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, resultSet.getInt("id"));
        ResultSet listAmenities = preparedStatement.executeQuery();
        while(listAmenities.next()) {
          listing.addAmenities(Amenities.valueOf(listAmenities.getString("amenity")));
        }
        listAmenities.close();

        sql = "SELECT renter_ins FROM relationshipRenterHost WHERE list_id = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, resultSet.getInt("id"));
        ResultSet listRenters = preparedStatement.executeQuery();
        while(listRenters.next()) {
          listing.setRenter_ins_history(listRenters.getInt("renter_ins"));
        }
        listRenters.close();

        listings.add(listing);
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
        while (hostListings.next()) {
          hosts.setOwnListings(hostListings.getInt("list_id"));
        }

        users.add(hosts);
        hostListings.close();
      }
      hostInfo.close();

    } catch (Exception e) {
      System.out.println("Something went wrong with load host from DB! see below details: ");
      e.printStackTrace();
    }
  }

  private Date parseStringToDate(String date) {
    try {
      return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    } catch (ParseException e) {
      System.out.println("Parse date error! see below details: ");
      e.printStackTrace();
      return null;
    }
  }

  private String parseDatetoString(Date date) {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    return dateFormat.format(date);
  }

  public ArrayList<Listings> getListings(){
    return listings;
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
      loadListingsFromDB(resultSet, connection);
      connection.close();
      statement.close();
    } catch (Exception e) {
      System.out.println("Something went wrong with select all from listing! see below details: ");
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
      loadListingsFromDB(resultSet, connection);
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
      loadListingsFromDB(resultSet, connection);
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
      loadListingsFromDB(resultSet, connection);
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