package org.jth.databaseHelper;

import org.jth.fields.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.jth.databaseHelper.DatabaseDriver.*;


public class DatabaseInsertHelperImpl implements DatabaseInsertHelper {

  private DatabaseCheckDataHelperImpl databaseCheckDataHelper = new DatabaseCheckDataHelperImpl();

//  public static void main(String[] args) {
//    DatabaseInsertHelperImpl databaseSelectHelper = new DatabaseInsertHelperImpl();
//    databaseSelectHelper.insertComment(1004, 1001, "haha", 5);
//  }

  @Override
  public int insertListings(double latitude, double longitude, String address, String postal_code,
                             ListingType listingType, double price, String city, String country, boolean availability) {
    try {
      Connection connection = connectingToDatabase();
      String sql = "INSERT INTO listings (latitude, longitude, address, postal_code, list_type, price, city, country, availability)" +
          "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
      PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setDouble(1, latitude);
      preparedStatement.setDouble(2, longitude);
      preparedStatement.setString(3, address);
      preparedStatement.setString(4, postal_code);
      preparedStatement.setString(5, listingType.name());
      preparedStatement.setDouble(6, price);
      preparedStatement.setString(7,city);
      preparedStatement.setString(8,country);
      preparedStatement.setBoolean(9,availability);
      int id = preparedStatement.executeUpdate();
      ResultSet resultSet = null;
      int listId = -1;
      if (id > 0) {
        resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
          listId = resultSet.getInt(1);
        }
      }
      preparedStatement.close();
      connection.close();
      return listId;
    } catch (Exception e) {
      System.out.println("Something went wrong with insert listing! see below details: ");
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public void insertAmenities(int list_id, Amenities amenities) {
    try {
      Connection connection = connectingToDatabase();
      String sql = "INSERT listingsAmenities (list_id, amenity) VALUES (?, ?);";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, list_id);
      preparedStatement.setString(2, amenities.name());
      preparedStatement.executeUpdate();
      preparedStatement.close();
      connection.close();
    } catch (Exception e) {
      System.out.println("Something went wrong with insert Amenities! see below details: ");
      e.printStackTrace();
    }
  }

  private void insertUsers(int ins, String firstName, String lastName, String address, String postal_code,
                           Date date_of_birth, String occupation){
    try {
      Connection connection = connectingToDatabase();
      String sql = "INSERT INTO Users VALUES (?, ?, ?, ?, ?, ?, ?);";
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
    if(!databaseCheckDataHelper.checkUserOrListExsits(ins, 1)) {
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
  }

  @Override
  public void insertHosts(int ins, String firstName, String lastName, String address, String postal_code,
                          Date date_of_birth, String occupation) {
    if(!databaseCheckDataHelper.checkUserOrListExsits(ins, 2)) {
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
  }



  @Override
  public void insertRelationshipRenterHost(int renterIns, int hostIns, int listId) {
    if(!databaseCheckDataHelper.checkFullRelationShipExsits(renterIns, hostIns, listId)) {
      try {
        Connection connection = connectingToDatabase();
        if (databaseCheckDataHelper.checkUserOrListExsits(renterIns, 1) &&
            databaseCheckDataHelper.checkUserOrListExsits(hostIns, 2) &&
            databaseCheckDataHelper.checkUserOrListExsits(listId, 3) &&
            databaseCheckDataHelper.checkHostOwnList(hostIns, listId)) {
          String sql = "INSERT relationshipRenterHost (renter_ins, host_ins, list_id) VALUES (?, ?, ?);";
          PreparedStatement preparedStatement = connection.prepareStatement(sql);
          preparedStatement.setInt(1, renterIns);
          preparedStatement.setInt(2, hostIns);
          preparedStatement.setInt(3, listId);
          preparedStatement.executeUpdate();
          preparedStatement.close();
          connection.close();
        } else {
          connection.close();
          System.out.println("Please check renter or host social insurance number");
        }
      } catch (Exception e) {
        System.out.println("Something went wrong with insert Relationship Renter Host! see below details: ");
        e.printStackTrace();
      }
    }
  }

  @Override
  public void insertComment(int fromIns, int toIns, String comment, int rate) {
    if(!databaseCheckDataHelper.checkCommentExsits(fromIns, toIns)) {
      try {
        Connection connection = connectingToDatabase();
        String sql = null;
        if ((databaseCheckDataHelper.checkUserOrListExsits(fromIns, 1) ||
            databaseCheckDataHelper.checkUserOrListExsits(fromIns, 2)) &&
            (databaseCheckDataHelper.checkUserOrListExsits(toIns, 1) ||
                databaseCheckDataHelper.checkUserOrListExsits(toIns, 2))) {
          // if from is renter
          if (databaseCheckDataHelper.checkCommentTarget(fromIns, toIns, 1) ||
              databaseCheckDataHelper.checkCommentTarget(fromIns, toIns, 2)) {
            sql = "INSERT commentTable (fromUsr, toUsr, content, rate) VALUES (?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, fromIns);
            preparedStatement.setInt(2, toIns);
            preparedStatement.setString(3, comment);
            preparedStatement.setInt(4, rate);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
          } else {
            connection.close();
            System.out.println("Wrong host and renter are do not have any relationship!");
          }
        } else {
          connection.close();
          System.out.println("Wrong social insurance number! no such user exists!");
        }
      } catch (Exception e) {
        System.out.println("Something went wrong with insert comment! see below details: ");
        e.printStackTrace();
      }
    }
  }

  @Override
  public void insertCommentListing(int fromIns, int list_id, String comment, int rate) {
    if(!databaseCheckDataHelper.checkCommentListingExsits(fromIns, list_id)) {
      try {
        Connection connection = connectingToDatabase();
        String sql = null;
        if (databaseCheckDataHelper.checkUserOrListExsits(fromIns, 1) &&
            databaseCheckDataHelper.checkUserOrListExsits(list_id, 3) &&
            databaseCheckDataHelper.checkListingRenterRelation(list_id, fromIns)) {
          sql = "INSERT commentOnListingTable (usrIns, listId, content, rate) VALUES (?, ?, ?, ?);";
          PreparedStatement preparedStatement = connection.prepareStatement(sql);
          preparedStatement.setInt(1, fromIns);
          preparedStatement.setInt(2, list_id);
          preparedStatement.setString(3, comment);
          preparedStatement.setInt(4, rate);
          preparedStatement.executeUpdate();
          preparedStatement.close();
          connection.close();
        } else {
          connection.close();
          System.out.println("Wrong social insurance number or listing does not exists or renter does not have relation!");
        }
      } catch (Exception e) {
        System.out.println("Something went wrong with insert Comment Listing! see below details: ");
        e.printStackTrace();
      }
    }
  }

  @Override
  public void insertHostOwnListings(int hostIns, int listId) {
    if(!databaseCheckDataHelper.checkHostOwnList(hostIns, listId)) {
      try {
        Connection connection = connectingToDatabase();
        String sql = null;
        if (databaseCheckDataHelper.checkUserOrListExsits(hostIns, 2) &&
            databaseCheckDataHelper.checkUserOrListExsits(listId, 3)) {
          sql = "INSERT hostOwnListings (list_id, host_ins) VALUES (?, ?);";
          PreparedStatement preparedStatement = connection.prepareStatement(sql);
          preparedStatement.setInt(1, listId);
          preparedStatement.setInt(2, hostIns);
          preparedStatement.executeUpdate();
          preparedStatement.close();
          connection.close();
        } else {
          connection.close();
          System.out.println("Listings or hosts does not exists!");
        }
      } catch (Exception e) {
        System.out.println("Something went wrong with insert Host Own Listings! see below details: ");
        e.printStackTrace();
      }
    }
  }

  @Override
  public void insertFutureBooking(int renterIns, int listId) {
    if(!databaseCheckDataHelper.checkFutureBooking(renterIns, listId)) {
      try {
        Connection connection = connectingToDatabase();
        if (databaseCheckDataHelper.checkUserOrListExsits(renterIns, 1) &&
            databaseCheckDataHelper.checkUserOrListExsits(listId, 3)) {
          String sql = "INSERT futeureBooking (renter_ins, list_id) VALUES (?, ?);";
          PreparedStatement preparedStatement = connection.prepareStatement(sql);
          preparedStatement.setInt(1, renterIns);
          preparedStatement.setInt(2, listId);
          preparedStatement.executeUpdate();
          preparedStatement.close();
          connection.close();
        } else {
          connection.close();
          System.out.println("Listing or renters does not exists!");
        }
      } catch (Exception e) {
        System.out.println("Something went wrong with insert future booking! see below details: ");
        e.printStackTrace();
      }
    }
  }


  @Override
  public void insertUnavailableTimes(int list_id, Date fromDate, Date toDate) {
    if(!databaseCheckDataHelper.checkUnavailableTimes(list_id, fromDate, toDate)) {
      try {
        Connection connection = connectingToDatabase();
        String sql = "INSERT INTO unavailable_times (list_id, fromTime, toTime) VALUES (?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, list_id);
        preparedStatement.setString(2, parseDate(fromDate));
        preparedStatement.setString(3, parseDate(toDate));
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
      } catch (Exception e) {
        System.out.println("Something went wrong with insert Unavailable Times! see below details: ");
        e.printStackTrace();
      }
    }
  }

  private static String parseDate(Date date) {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    return dateFormat.format(date);
  }
}