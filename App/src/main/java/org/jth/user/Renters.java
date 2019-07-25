package org.jth.user;

import org.jth.listings.Listings;

import java.util.ArrayList;
import java.util.Date;

public class Renters extends Users {
  private int renter_id;
  private String card_number;
  private String card_expiry_date;
  private int cvv;
  private ArrayList<Integer> furtureBooking = new ArrayList<>();

  public Renters(int social_insurance_number, String first_name, String last_name, String address, String postal_code,
                 Date date_of_birth, String occupation, int renter_id,
                 String card_number, String card_expiry_date, int cvv) {
    super(social_insurance_number, first_name, last_name, address, postal_code, date_of_birth, occupation);
    this.renter_id = renter_id;
    this.card_number = card_number;
    this.card_expiry_date = card_expiry_date;
    this.cvv = cvv;
  }

  public void addBooking(int list_id) {
    furtureBooking.add(list_id);
  }

  public int getRenter_id() {
    return renter_id;
  }

  public String getCard_number(){
    return card_number;
  }

  public String getCard_expiry_date(){
    return card_expiry_date;
  }

  public int getCvv() {
    return cvv;
  }

  public ArrayList<Integer> getFurtureBooking() {
    return furtureBooking;
  }

}
