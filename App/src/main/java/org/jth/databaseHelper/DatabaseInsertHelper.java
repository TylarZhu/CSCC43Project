package org.jth.databaseHelper;

import org.jth.fields.*;

import java.util.Date;

public interface DatabaseInsertHelper {
  void insertListings(double latitude, double longitude, String address, String postal_code, ListingType listingType, double price, Amenities amenities, String city, String country);
  void insertUnavailableTimes(int list_id, Date date);
  void insertUsers(int ins, String address, String postal_code, Date date_of_birth, String occupation);
}
