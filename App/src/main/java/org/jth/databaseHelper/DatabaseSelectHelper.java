package org.jth.databaseHelper;

public interface DatabaseSelectHelper {
  void selectAllListings();
  void selectAllUnavailableTime();
  void selectListingIdsAndTimesByUnavailableTime();
  void selectListingsByPostalCode(String postalCode);
  void selectListingsByLatitudeLongitude(double latitude, double longitude, double distance);
  void selectListingsByAddress(String address);
}
