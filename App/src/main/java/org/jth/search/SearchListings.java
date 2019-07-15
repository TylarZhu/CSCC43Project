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

  private void sortByPriceLowToHigh() {
    for(int i = 0; i < listings.size(); i ++) {
      for(int j = 0; j < listings.size(); j ++) {
        if(listings.get(i).getPrice() < listings.get(j).getPrice()) {
          Collections.swap(listings, i, j);
        }
      }
    }
  }

  public ArrayList<Listings> getAllListingsSortByPrice() {
    databaseSelectHelperImpl.selectAllListings();
    listings = databaseSelectHelperImpl.getListings();
    sortByPriceLowToHigh();
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

  public ArrayList<Listings> getListingsByPostalCode(String postal_code) {
    databaseSelectHelperImpl.selectListingsByPostalCode(postal_code);
    listings = databaseSelectHelperImpl.getListings();
    sortByPriceLowToHigh();
    return listings;
  }

  public ArrayList<Listings> getListingsByAddress(String address) {
    databaseSelectHelperImpl.selectListingsByAddress(address);
    listings = databaseSelectHelperImpl.getListings();
    sortByPriceLowToHigh();
    return listings;
  }
}
