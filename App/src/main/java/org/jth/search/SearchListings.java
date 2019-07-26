package org.jth.search;


import org.jth.fields.Amenities;
import org.jth.listings.Listings;
import org.jth.databaseHelper.DatabaseSelectHelperImpl;

import java.util.ArrayList;
import java.util.Date;


public class SearchListings {

  public void getListingsByDataRange(Date from, Date to, ArrayList<Listings> listings){
    for(int i = 0; i < listings.size(); i++){
      if(! listings.get(i).checkBooking(from, to)){
          listings.remove(i);
          i--;
      }
    }
  }

  public void getListingsAmenitiesByPostcode(String post, ArrayList<Listings> listings) {
    for (int i = 0; i < listings.size(); i++) {
      if (!listings.get(i).getPostal_code().equals(post)) {
          listings.remove(i);
          i--;
      }
    }
  }
}
