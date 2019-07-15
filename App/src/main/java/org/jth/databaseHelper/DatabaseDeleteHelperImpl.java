package org.jth.databaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static org.jth.databaseHelper.DatabaseDriver.connectingToDatabase;

public class DatabaseDeleteHelperImpl implements DatabaseDeleteHelper {

  @Override
  public void deleteListingById(int id) {
    try {
      Connection connection = connectingToDatabase();
      String sql = "DELETE FROM listings WHERE id = ?;";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, id);
      preparedStatement.executeUpdate();
      preparedStatement.close();
      connection.close();
    } catch (Exception e) {
      System.out.println("Something went wrong with delete listing by id! see below details: ");
      e.printStackTrace();
    }
  }
}
