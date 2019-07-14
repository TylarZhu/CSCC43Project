package org.jth.listings;

import org.jth.fields.Amenities;
import org.jth.fields.ListingType;

public class Listings {
  private int id;
  private double latitude;
  private double longitude;
  private String address;
  private String postal_code;
  private ListingType list_type;
  private double price;
  private Amenities amenities;
  private String city;
  private String country;

  public Listings(int id,
                  double latitude,
                  double longitude,
                  String address,
                  String postal_code,
                  ListingType listing_type,
                  double price,
                  Amenities amenities,
                  String city,
                  String country) {
    this.id = id;
    this.latitude = latitude;
    this.longitude = longitude;
    this.address = address;
    this.postal_code = postal_code;
    this.list_type = listing_type;
    this.price = price;
    this.amenities = amenities;
    this.city = city;
    this.country = country;
  }

  public double getPrice(){
    return price;
  }
}
