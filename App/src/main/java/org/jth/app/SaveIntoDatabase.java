package org.jth.app;

import org.jth.databaseHelper.DatabaseInsertHelperImpl;
import org.jth.fields.Amenities;
import org.jth.listings.Listings;
import org.jth.user.Hosts;
import org.jth.user.RenterHostListingRelationship;
import org.jth.user.Renters;
import org.jth.user.Users;

import java.util.ArrayList;
import java.util.Date;

public class SaveIntoDatabase {
  private DatabaseInsertHelperImpl databaseInsertHelper = new DatabaseInsertHelperImpl();

  public void saveUser(ArrayList<Users> users, int choice) {
    if(choice == 1) {
      for(Users user: users){
        Renters renter = (Renters) user;
        databaseInsertHelper.insertRentors(renter.getSocial_insurance_number(),
            renter.getFirst_name(), renter.getLast_name(), renter.getAddress(), renter.getPostal_code(),
            renter.getDate_of_birth(), renter.getOccupation(), renter.getCard_number(), renter.getCard_expiry_date(),
            renter.getCvv());
        for(Integer list_id: renter.getFurtureBooking()) {
          databaseInsertHelper.insertFutureBooking(user.getSocial_insurance_number(), list_id);
        }
      }
    } else {
      for(Users user: users) {
        Hosts hosts = (Hosts) user;
        databaseInsertHelper.insertHosts(hosts.getSocial_insurance_number(), hosts.getFirst_name(),
            hosts.getLast_name(), hosts.getAddress(), hosts.getPostal_code(), hosts.getDate_of_birth(),
            hosts.getOccupation());
        for(Integer list_id: hosts.getOwnListings()) {
          databaseInsertHelper.insertHostOwnListings(hosts.getSocial_insurance_number(),
              hosts.getSocial_insurance_number());
        }
      }
    }
  }

  public void saveRenterHostListing(RenterHostListingRelationship renterHostListingRelationship){
    for(ArrayList<Integer> relationship: renterHostListingRelationship.getRelation()) {
      databaseInsertHelper.insertRelationshipRenterHost(relationship.get(0), relationship.get(1), relationship.get(2));
    }
  }

  public void saveUnavailableTime(ArrayList<Listings> listings) {
    for(Listings listing: listings) {
      for(ArrayList<Date> dates: listing.getUnavailableTime()) {
        databaseInsertHelper.insertUnavailableTimes(listing.getId(), dates.get(0), dates.get(1));
      }
    }
  }

  public void saveAmenities(ArrayList<Listings> listings) {
    for(Listings listing: listings) {
      for (Amenities amenities: listing.getAmenities()){
        databaseInsertHelper.insertAmenities(listing.getId(), amenities);
      }
    }
  }
}
