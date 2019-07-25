package org.jth.app;

import org.jth.databaseHelper.DatabaseDriver;

import java.sql.Connection;

public class Main {



  public static void main(String[] args) {
    try {
      DatabaseDriver databaseDriver = new DatabaseDriver();
      Connection connection = DatabaseDriver.connectingToDatabase();
      DatabaseDriver.dropDatabase(connection);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
