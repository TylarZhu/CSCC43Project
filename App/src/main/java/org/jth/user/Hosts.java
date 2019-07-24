package org.jth.user;

import org.jth.listings.Listings;

import java.util.ArrayList;
import java.util.Date;

public class Hosts extends Users {
  private ArrayList<Integer> ownListings;
  private int hostId;

  public Hosts(int social_insurance_number, String first_name, String last_name, String address,
               String postal_code, Date date_of_birth, String occupation, int hostId) {
    super(social_insurance_number, first_name, last_name, address, postal_code, date_of_birth, occupation);
    this.hostId = hostId;
    this.ownListings = null;
  }

  public void setOwnListings(ArrayList<Integer> listings) {
    this.ownListings = listings;
  }
}
