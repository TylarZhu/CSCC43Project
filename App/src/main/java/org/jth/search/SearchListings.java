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
    ArrayList<Listings> list = searchListings.getAllListingsSortByPriceLowToHigh();
    for(int i = 0; i < list.size(); i ++) {
      System.out.println(list.get(i).getPrice());
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
}
