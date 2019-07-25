package org.jth.search;

import org.jth.listings.Listings;
import org.jth.databaseHelper.DatabaseSelectHelperImpl;

import java.util.ArrayList;
import java.util.Date;

public class SearchListings {
  private ArrayList<Listings> listings = null;
  private DatabaseSelectHelperImpl databaseSelectHelperImpl = new DatabaseSelectHelperImpl();



  public ArrayList<Listings> getSearchListings() {
    return listings;
  }

  public void getAllListingsSortByPrice(int choice) {
    databaseSelectHelperImpl.selectAllListings(choice);
    listings = databaseSelectHelperImpl.getListings();
  }

  public void getListingsFromLatitudeLongitudeSortByDistance(double latitude, double longitude, double distance) {
    databaseSelectHelperImpl.selectListingsByLatitudeLongitude(latitude, longitude, distance);
    listings = databaseSelectHelperImpl.getListings();
  }

  public void getListingsByPostalCode(String postal_code, int choice) {
    databaseSelectHelperImpl.selectListingsByPostalCode(postal_code, choice);
    listings = databaseSelectHelperImpl.getListings();
  }

  public void getListingsByAddress(String address, int choice) {
    databaseSelectHelperImpl.selectListingsByAddress(address, choice);
    listings = databaseSelectHelperImpl.getListings();
  }
}
