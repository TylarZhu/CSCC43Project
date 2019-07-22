package org.jth.databaseHelper;

public interface DatabaseSelectHelper {
  void selectAllListings(int choice);
  void selectAllUnavailableTime();
  void selectListingIdsAndTimesByUnavailableTime();
  void selectListingsByPostalCode(String postalCode, int choice);
  void selectListingsByLatitudeLongitude(double latitude, double longitude, double distance);
  void selectListingsByAddress(String address, int choice);
}
