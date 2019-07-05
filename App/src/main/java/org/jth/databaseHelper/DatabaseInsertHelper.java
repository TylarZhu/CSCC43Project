package org.jth.databaseHelper;

import org.jth.Fields.*;

public interface DatabaseInsertHelper {
  void insertListings(double latitude, double longitude, String address, String postal_code, ListingType listingType, double price, Amenities amenities);
}
