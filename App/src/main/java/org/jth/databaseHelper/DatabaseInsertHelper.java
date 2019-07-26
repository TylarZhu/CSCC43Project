package org.jth.databaseHelper;

import org.jth.fields.*;

import java.util.Date;

public interface DatabaseInsertHelper {
  void insertListings(double latitude, double longitude, String address, String postal_code,
                      ListingType listingType, double price, String city, String country, boolean availability);

  void insertAmenities(int list_id, Amenities amenities);

  void insertUnavailableTimes(int list_id, Date fromDate, Date toDate);

  //void insertUsers(int ins, String firstName, String lastName, String address, String postal_code, Date date_of_birth, String occupation);

  void insertRentors(int ins, String firstName, String lastName, String address, String postal_code, Date date_of_birth,
                     String occupation, String cardNumnber, String card_expiry_date, int cvv);

  void insertHosts(int ins, String firstName, String lastName, String address, String postal_code, Date date_of_birth,
                     String occupation);

  void insertRelationshipRenterHost(int renterIns, int hostIns, int listId);

  void insertComment(int fromIns, int toIns, String comment, int rate);

  void insertCommentListing(int fromIns, int list_id, String comment, int rate);

  void insertHostOwnListings(int hostIns, int listId);

  void insertFutureBooking(int renterIns, int listId);
}
