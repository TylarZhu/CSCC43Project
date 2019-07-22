package org.jth.search;

import org.jth.fields.Amenities;
import org.jth.fields.ListingType;
import org.jth.listings.Listings;
import org.jth.databaseHelper.DatabaseSelectHelperImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchListings {
  private ArrayList<Listings> listings = null;
  private DatabaseSelectHelperImpl databaseSelectHelperImpl = new DatabaseSelectHelperImpl();

  public static void main(String[] args) {
    SearchListings searchListings = new SearchListings();
    ArrayList<Listings> listings1 = searchListings.getListingsFromLatitudeLongitudeSortByDistance(11, 11, 3);
    for(int i = 0; i < listings1.size(); i ++) {
      System.out.println(listings1.get(i).getAddress());
    }
  }

  public ArrayList<Listings> getAllListingsSortByPrice(int choice) {
    databaseSelectHelperImpl.selectAllListings(choice);
    listings = databaseSelectHelperImpl.getListings();
    return listings;
  }

  public ArrayList<Listings> getListingsFromLatitudeLongitudeSortByDistance(double latitude, double longitude, double distance) {
    databaseSelectHelperImpl.selectListingsByLatitudeLongitude(latitude, longitude, distance);
    listings = databaseSelectHelperImpl.getListings();
    return listings;
  }

  public ArrayList<Listings> getListingsByPostalCode(String postal_code, int choice) {
    databaseSelectHelperImpl.selectListingsByPostalCode(postal_code, choice);
    listings = databaseSelectHelperImpl.getListings();
    return listings;
  }

  public ArrayList<Listings> getListingsByAddress(String address, int choice) {
    databaseSelectHelperImpl.selectListingsByAddress(address, choice);
    listings = databaseSelectHelperImpl.getListings();
    return listings;
  }
}
