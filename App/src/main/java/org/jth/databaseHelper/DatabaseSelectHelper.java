package org.jth.databaseHelper;

import java.util.Date;

public interface DatabaseSelectHelper {
  void selectAllListings(int choice);
  void selectListingsByPostalCode(String postalCode, int choice);
  void selectListingsByLatitudeLongitude(double latitude, double longitude, double distance);
  void selectListingsByAddress(String address, int choice);
  void selectAllUsers(int choice);
  void selectAllComment();
}
