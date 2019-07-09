package org.jth.databaseHelper;

import java.sql.*;

public interface DatabaseSelectHelper {
  ResultSet selectAllFromListings();
  ResultSet selectAllFromUnavailableTime();
  ResultSet selectAllUnavailableListingsIds();
}
