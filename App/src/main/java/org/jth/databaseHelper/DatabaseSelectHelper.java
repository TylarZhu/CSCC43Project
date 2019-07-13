package org.jth.databaseHelper;

import java.sql.*;

public interface DatabaseSelectHelper {
  ResultSet selectAllListings();
  ResultSet selectAllUnavailableTime();
  ResultSet selectListingIdsAndTimesByUnavailableTime();
  ResultSet selectListingsByPostalCode(String postalCode);
  ResultSet selectListingsByLatitudeLongitude(double latitude, double longitude, double distance);
}
