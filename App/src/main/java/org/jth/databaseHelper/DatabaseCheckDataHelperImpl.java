package org.jth.databaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}
