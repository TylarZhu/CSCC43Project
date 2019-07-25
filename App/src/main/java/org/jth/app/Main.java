package org.jth.app;

import org.jth.databaseHelper.DatabaseDriver;
import org.jth.databaseHelper.DatabaseInsertHelperImpl;
import org.jth.databaseHelper.DatabaseSelectHelperImpl;
import org.jth.fields.Amenities;
import org.jth.fields.ListingType;
import org.jth.listings.Listings;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.jth.fields.Amenities.*;
import static org.jth.fields.ListingType.*;

public class Main {



  public static void main(String[] args) {
    try {
//      DatabaseDriver databaseDriver = new DatabaseDriver();
//      Connection connection = DatabaseDriver.connectingToDatabase();
//      DatabaseDriver.initializeDatabase(connection);
//
//      DatabaseInsertHelperImpl databaseInsertHelper = new DatabaseInsertHelperImpl();
//      databaseInsertHelper.insertListings(11, 11, "70 TOWN CENTER", "0B2 0P3",
//          APARTMENT, 1, "TORONTO", "CANANDA");
//      databaseInsertHelper.insertAmenities(1, KITCHEN);
//      databaseInsertHelper.insertAmenities(1, HEATING);
//      databaseInsertHelper.insertAmenities(1, WASHER);
//      databaseInsertHelper.insertAmenities(1, WIFI);

//      databaseInsertHelper.insertUnavailableTimes(1, parseStringToDate("2018-01-08"), parseStringToDate("2018-01-10"));
//      databaseInsertHelper.insertUnavailableTimes(1, parseStringToDate("2018-03-12"), parseStringToDate("2018-03-20"));
//      databaseInsertHelper.insertUnavailableTimes(1, parseStringToDate("2018-07-15"), parseStringToDate("2018-01-20"));


//      databaseInsertHelper.insertListings(9, 9, "80 TOWN CENTER", "0B2 0P10",
//          BOUNTIQUE_HOTEL, 3, "TORONTO", "CANANDA");
//      databaseInsertHelper.insertAmenities(2, IRON);
//      databaseInsertHelper.insertAmenities(2, LAPTOP_FRIENDLY_WORKSPACE);
//      databaseInsertHelper.insertAmenities(2, SELF_CHECK_IN);
//      databaseInsertHelper.insertAmenities(2, CARBON_MONOXIDE_DETECTOR);

//      databaseInsertHelper.insertUnavailableTimes(2, parseStringToDate("2018-01-15"), parseStringToDate("2018-01-20"));
//      databaseInsertHelper.insertUnavailableTimes(2, parseStringToDate("2018-02-07"), parseStringToDate("2018-02-10"));
//      databaseInsertHelper.insertUnavailableTimes(2, parseStringToDate("2018-10-15"), parseStringToDate("2018-10-20"));

//      databaseInsertHelper.insertListings(10, 10, "100 TOWN CENTER", "0B2 0P1",
//          BOUNTIQUE_HOTEL, 4, "TORONTO", "CANANDA");
//      databaseInsertHelper.insertAmenities(3, SHAMPOO);
//      databaseInsertHelper.insertAmenities(3, AIR_CONDITIONING);
//      databaseInsertHelper.insertAmenities(3, DRYER);
//      databaseInsertHelper.insertAmenities(3, BREAKFAST);

//      databaseInsertHelper.insertUnavailableTimes(3, parseStringToDate("2018-01-01"), parseStringToDate("2018-01-09"));
//      databaseInsertHelper.insertUnavailableTimes(3, parseStringToDate("2018-09-12"), parseStringToDate("2018-09-20"));
//      databaseInsertHelper.insertUnavailableTimes(3, parseStringToDate("2018-07-01"), parseStringToDate("2018-07-02"));

//      databaseInsertHelper.insertListings(10, 11, "110 TOWN CENTER", "0B2 0P6",
//          BOUNTIQUE_HOTEL, 35, "TORONTO", "CANANDA");
//      databaseInsertHelper.insertAmenities(4, GANGERS);
//      databaseInsertHelper.insertAmenities(4, HAIR_DRYER);
//      databaseInsertHelper.insertAmenities(4, TV);
//      databaseInsertHelper.insertAmenities(4, HIGH_CHAIR);

//      databaseInsertHelper.insertUnavailableTimes(4, parseStringToDate("2018-06-16"), parseStringToDate("2018-06-19"));
//      databaseInsertHelper.insertUnavailableTimes(4, parseStringToDate("2018-11-12"), parseStringToDate("2018-11-20"));
//      databaseInsertHelper.insertUnavailableTimes(4, parseStringToDate("2018-05-17"), parseStringToDate("2018-05-18"));

      DatabaseSelectHelperImpl databaseSelectHelper = new DatabaseSelectHelperImpl();
      databaseSelectHelper.selectAllListings(1);
      ArrayList<Listings> thisListings = databaseSelectHelper.getListings();
//      for(Listings listings: thisListings) {
//        System.out.println("***********************");
//        System.out.println(listings.getId());
//        System.out.println(listings.getPrice());
//        System.out.println(listings.getAddress());
//        for(Amenities amenities: listings.getAmenities()) {
//          System.out.println(amenities.name());
//        }
//        for(ArrayList<Date> dates: listings.getUnavailableTime()){
//          for(Date date: dates) {
//            System.out.println(date.toString());
//          }
//        }
//      }

      thisListings.get(3).changePrice(12, parseStringToDate("2018-02-11"));
      System.out.println(thisListings.get(3).getPrice());

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static Date parseStringToDate(String date) {
    try {
      return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    } catch (ParseException e) {
      System.out.println("Parse date error! see below details: ");
      e.printStackTrace();
      return null;
    }
  }

}
