package org.jth.search;

import org.jth.fields.Amenities;
import org.jth.fields.ListingType;
import org.jth.listings.Listings;
import org.jth.databaseHelper.DatabaseSelectHelperImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;

public class SearchListings {
  public static void main(String[] args) {
    SearchListings searchListings = new SearchListings();
    ArrayList<Listings> list = searchListings.getListingsFromLatitudeLongitudeSortByDistance(11, 11, 1);
    for(int i = 0; i < list.size(); i ++) {
      System.out.println(list.get(i).getAddress());
    }
  }

  private ArrayList<Listings> listings = null;
  private DatabaseSelectHelperImpl databaseSelectHelperImpl = new DatabaseSelectHelperImpl();

  public ArrayList<Listings> getAllListingsSortByPriceLowToHigh() {
    databaseSelectHelperImpl.selectAllListings();
    listings = databaseSelectHelperImpl.getListings();
    for(int i = 0; i < listings.size(); i ++) {
      for(int j = 0; j < listings.size(); j ++) {
        if(listings.get(i).getPrice() < listings.get(j).getPrice()) {
          Collections.swap(listings, i, j);
        }
      }
    }
    return listings;
  }

  public ArrayList<Listings> getListingsFromLatitudeLongitudeSortByDistance(double latitude, double longitude, double distance) {
    databaseSelectHelperImpl.selectListingsByLatitudeLongitude(latitude, longitude, distance);
    listings = databaseSelectHelperImpl.getListings();
    // a ^ 2 + b ^ 2 = c ^ 2
    for(int i = 0; i < listings.size(); i ++) {
      for(int j = 0; j < listings.size(); j ++) {
        double di = Math.sqrt(Math.pow((latitude - listings.get(i).getLatitude()), 2) +
            Math.pow((longitude - listings.get(i).getLongitude()), 2)),
            dj = Math.sqrt(Math.pow((latitude - listings.get(j).getLatitude()), 2) +
                Math.pow((longitude - listings.get(j).getLongitude()), 2));
        if(di < dj) {
          Collections.swap(listings, i, j);
        }
      }
    }
    return listings;
  }
}
