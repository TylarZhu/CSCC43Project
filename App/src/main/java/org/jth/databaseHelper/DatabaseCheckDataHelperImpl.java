package org.jth.databaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.jth.databaseHelper.DatabaseDriver.connectingToDatabase;

public class DatabaseCheckDataHelperImpl implements DatabaseCheckDataHelper {
  @Override
  public boolean checkUserOrListExsits(int ins, int choice) {
    try {
      Connection connection = connectingToDatabase();
      String sql = null;
      if(choice == 1) {
        sql = "SELECT EXISTS(SELECT * FROM renters WHERE renter_profile = ?);";
      } else if(choice == 2) {
        sql = "SELECT EXISTS(SELECT * FROM hosts WHERE host_profile = ?);";
      } else {
        sql = "SELECT EXISTS(SELECT * FROM listings WHERE id = ?);";
      }
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, ins);
      ResultSet resultSet = preparedStatement.executeQuery();
      resultSet.next();
      if(resultSet.getInt(1) == 1) {
        preparedStatement.close();
        connection.close();
        resultSet.close();
        return true;
      } else {
        preparedStatement.close();
        connection.close();
        resultSet.close();
        return false;
      }
    } catch (Exception e) {
      System.out.println("Something went wrong with check User Or List Exsits! see below details: ");
      e.printStackTrace();
      return false;
    }
  }



  @Override
  public boolean checkCommentTarget(int fromIns, int toIns, int choice) {
    try {
      Connection connection = connectingToDatabase();
      String sql = null;
      if (choice == 1) {
        sql = "SELECT DISTINCT host_ins FROM relationshipRenterHost WHERE renter_ins = ?;";
      } else {
        sql = "SELECT DISTINCT renter_ins FROM relationshipRenterHost WHERE host_ins = ?;";
      }
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, fromIns);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        if (resultSet.getInt(1) == toIns) {
          preparedStatement.close();
          connection.close();
          resultSet.close();
          return true;
        }
      }
      preparedStatement.close();
      connection.close();
      resultSet.close();
      return false;
    } catch (Exception e) {
      System.out.println("Something went wrong with check Comment Target! see below details: ");
      e.printStackTrace();
      return false;
    }
  }
  @Override
  public boolean checkHostOwnList(int ins, int listId) {
    try {
      Connection connection = connectingToDatabase();
      String sql = "SELECT EXISTS(SELECT * FROM hostOwnListings WHERE host_ins = ? AND list_id = ?);";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, ins);
      preparedStatement.setInt(2, listId);
      ResultSet resultSet = preparedStatement.executeQuery();
      resultSet.next();
      if(resultSet.getInt(1) == 1) {
        preparedStatement.close();
        connection.close();
        resultSet.close();
        return true;
      } else {
        System.out.println("Host does not own this house !");
        preparedStatement.close();
        connection.close();
        resultSet.close();
        return false;
      }
    } catch (Exception e) {
      System.out.println("Something went wrong with check host own list! see below details: ");
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean checkListingStatus(int id) {
    try{
      String sql = null;
      Connection connection = connectingToDatabase();
      sql = "SELECT EXISTS(SELECT * FROM relationshipRenterHost WHERE list_id = ?);";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      resultSet.next();
      if(resultSet.getInt(1) == 1){
        preparedStatement.close();
        connection.close();
        return true;
      }else{
        preparedStatement.close();
        connection.close();
        return false;
      }
    }catch (Exception e){
      System.out.println("something went wrong with check listing status! see below details:");
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean checkListingRenterRelation(int list_id, int renterIns) {
    try{
      String sql = null;
      Connection connection = connectingToDatabase();
      sql = "SELECT EXISTS(SELECT * FROM relationshipRenterHost WHERE list_id = ? AND renter_ins = ?);";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, list_id);
      preparedStatement.setInt(2, renterIns);
      ResultSet resultSet = preparedStatement.executeQuery();
      resultSet.next();
      if(resultSet.getInt(1) == 1){
        preparedStatement.close();
        connection.close();
        return true;
      }else{
        preparedStatement.close();
        connection.close();
        return false;
      }
    }catch (Exception e){
      System.out.println("something went wrong with check Listing Renter Relation! see below details:");
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean checkFullRelationShipExsits(int renterIns, int hostIns, int listId) {
    try{
      String sql = null;
      Connection connection = connectingToDatabase();
      sql = "SELECT EXISTS(SELECT * FROM relationshipRenterHost WHERE list_id = ? AND renter_ins = ? AND host_ins = ?);";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, listId);
      preparedStatement.setInt(2, renterIns);
      preparedStatement.setInt(3, hostIns);
      ResultSet resultSet = preparedStatement.executeQuery();
      resultSet.next();
      if(resultSet.getInt(1) == 1){
        preparedStatement.close();
        connection.close();
        return true;
      }else{
        preparedStatement.close();
        connection.close();
        return false;
      }
    }catch (Exception e){
      System.out.println("something went wrong with check Full Relation Ship Exsits! see below details:");
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean checkCommentExsits(int fromIns, int toIns) {
    try{
      String sql = null;
      Connection connection = connectingToDatabase();
      sql = "SELECT EXISTS(SELECT * FROM commentTable WHERE fromUsr = ? AND toUsr = ?);";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, fromIns);
      preparedStatement.setInt(2, toIns);
      ResultSet resultSet = preparedStatement.executeQuery();
      resultSet.next();
      if(resultSet.getInt(1) == 1){
        preparedStatement.close();
        connection.close();
        return true;
      }else{
        preparedStatement.close();
        connection.close();
        return false;
      }
    }catch (Exception e){
      System.out.println("something went wrong with check Comment Exsits! see below details:");
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean checkCommentListingExsits(int fromIns, int list_id) {
    try{
      String sql = null;
      Connection connection = connectingToDatabase();
      sql = "SELECT EXISTS(SELECT * FROM commentOnListingTable WHERE usrIns = ? AND listId = ?);";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, fromIns);
      preparedStatement.setInt(2, list_id);
      ResultSet resultSet = preparedStatement.executeQuery();
      resultSet.next();
      if(resultSet.getInt(1) == 1){
        preparedStatement.close();
        connection.close();
        return true;
      }else{
        preparedStatement.close();
        connection.close();
        return false;
      }
    }catch (Exception e){
      System.out.println("something went wrong with check Comment Listing Exsits! see below details:");
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean checkFutureBooking(int renterIns, int listId) {
    try{
      String sql = null;
      Connection connection = connectingToDatabase();
      sql = "SELECT EXISTS(SELECT * FROM futeureBooking WHERE renter_ins = ? AND list_id = ?);";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, renterIns);
      preparedStatement.setInt(2, listId);
      ResultSet resultSet = preparedStatement.executeQuery();
      resultSet.next();
      if(resultSet.getInt(1) == 1){
        preparedStatement.close();
        connection.close();
        return true;
      }else{
        preparedStatement.close();
        connection.close();
        return false;
      }
    }catch (Exception e){
      System.out.println("something went wrong with check Future Booking! see below details:");
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean checkUnavailableTimes(int list_id, Date fromDate, Date toDate) {
    try{
      String sql = null;
      Connection connection = connectingToDatabase();
      sql = "SELECT EXISTS(SELECT * FROM unavailable_times WHERE list_id = ? AND fromTime = ? AND toTime = ?);";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, list_id);
      preparedStatement.setString(2, parseDate(fromDate));
      preparedStatement.setString(3, parseDate(toDate));
      ResultSet resultSet = preparedStatement.executeQuery();
      resultSet.next();
      if(resultSet.getInt(1) == 1){
        preparedStatement.close();
        connection.close();
        return true;
      }else{
        preparedStatement.close();
        connection.close();
        return false;
      }
    }catch (Exception e){
      System.out.println("something went wrong with check Unavailable Times! see below details:");
      e.printStackTrace();
      return false;
    }
  }

  private static String parseDate(Date date) {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    return dateFormat.format(date);
  }
}
