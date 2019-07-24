package org.jth.databaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static org.jth.databaseHelper.DatabaseDriver.connectingToDatabase;

public class DatabaseDeleteHelperImpl implements DatabaseDeleteHelper {

  private DatabaseCheckDataHelperImpl databaseCheckDataHelper = new DatabaseCheckDataHelperImpl();

  @Override
  public void deleteListingById(int ins, int id) {
    try {
      Connection connection = connectingToDatabase();
      if(databaseCheckDataHelper.checkUserOrListExsits(2, ins) &&
          databaseCheckDataHelper.checkUserOrListExsits(3, id)) {
        String sql = "DELETE FROM unavailable_times WHERE list_id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();

        sql = "DELETE FROM hostOwnListings WHERE list_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();

        sql = "DELETE FROM rentalHistory WHERE list_id = ?;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();

        sql = "DELETE FROM listings WHERE id = 1;";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
      }
    } catch (Exception e) {
      System.out.println("Something went wrong with delete listing by id! see below details: ");
      e.printStackTrace();
    }
  }

  @Override
  public void deleteUserByIns(int ins) {
    try {
      Connection connection = connectingToDatabase();
      String sql = null;
      if(databaseCheckDataHelper.checkUserOrListExsits(ins, 1)) {
        sql = "DELETE FROM renters WHERE renter_profile = ?";
      } else if (databaseCheckDataHelper.checkUserOrListExsits(ins, 2)) {
        sql = "DELETE FROM hosts WHERE host_profile = ?";
      } else {
        System.out.println("User does not exists!");
        return;
      }
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, ins);
      preparedStatement.executeUpdate();

      sql = "DELETE FROM users WHERE social_insurance_number = ?";
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, ins);
      preparedStatement.executeUpdate();
      preparedStatement.close();
      connection.close();
    } catch (Exception e) {
      System.out.println("Something went wrong with delete User By Ins! see below details: ");
      e.printStackTrace();
    }
  }


}
