package org.jth.listings;

import org.jth.fields.Amenities;
import org.jth.fields.ListingType;

import java.util.ArrayList;
import java.util.Date;

public class Listings {
  private int id;
  private double latitude;
  private double longitude;
  private String address;
  private String postal_code;
  private ListingType list_type;
  private double price;
  private String city;
  private String country;
  private boolean availability;

  private ArrayList<Amenities> amenities = new ArrayList<>();
  private ArrayList<ArrayList<Date>> unavailableTime = new ArrayList<>();
  private ArrayList<Integer> renter_ins_history = new ArrayList<>();


  public Listings(int id,
                  double latitude,
                  double longitude,
                  String address,
                  String postal_code,
                  ListingType listing_type,
                  double price,
                  String city,
                  String country,
                  boolean availability) {
    this.id = id;
    this.latitude = latitude;
    this.longitude = longitude;
    this.address = address;
    this.postal_code = postal_code;
    this.list_type = listing_type;
    this.price = price;
    this.city = city;
    this.country = country;
    this.availability = availability;
  }

  public void addAmenities(Amenities amenities) {
    this.amenities.add(amenities);
  }

  public void setUnavailableTime(Date from, Date to) {
    ArrayList<Date> fromToDate = new ArrayList<>();
    if(from.before(to)) {
      fromToDate.add(from);
      fromToDate.add(to);
    } else {
      System.out.println("Wrong Date! Cannot put the date backwards!");
    }
    unavailableTime.add(fromToDate);
  }

  public void setRenter_ins_history(int ins) {
    renter_ins_history.add(ins);
  }

  public boolean checkBooking(Date from, Date to) {
    if(this.availability) {
      for (ArrayList<Date> dates : unavailableTime) {
        if (!(to.before(dates.get(0)) || from.after(dates.get(1)))) {
          System.out.println("Listings is been booked at this time range!");
          return false;
        }
      }
    } else {
      System.out.println("This listing is not availability right now!");
      return false;
    }
    return true;
  }

  public void changePrice(double price, Date today) {
    for(ArrayList<Date> dates: unavailableTime) {
      if(today.before(dates.get(0))||
          today.equals(dates.get(0)) ||
          (today.after(dates.get(0)) && today.before(dates.get(1))) ||
          today.equals(dates.get(1))) {
        System.out.println("you cannot change the price! This list is already be booked!");
        return;
      }
    }
    this.price = price;
  }

  public void changeAvailability(boolean availability, Date today) {
    for(ArrayList<Date> dates: unavailableTime) {
      if (today.after(dates.get(0)) && today.before(dates.get(1))) {
        System.out.println("You Cannot change availability! This list is still booking!");
        return;
      }
    }
    this.availability = availability;
  }

  public double getPrice(){
    return price;
  }

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public String getAddress() {
    return address;
  }

  public String getPostal_code(){
    return postal_code;
  }

  public ListingType getList_type(){
    return list_type;
  }

  public int getId(){
    return id;
  }

  public String getCity() {
    return city;
  }

  public String getCountry(){
    return country;
  }

  public ArrayList<ArrayList<Date>> getUnavailableTime(){
    return unavailableTime;
  }

  public boolean getAvailability() {
    return availability;
  }

  public ArrayList<Amenities> getAmenities(){
    return amenities;
  }

  public ArrayList<Integer> getRenter_ins_history(){
    return renter_ins_history;
  }
}