package org.jth.app;

import org.jth.databaseHelper.*;
import org.jth.comment.Comment;
import org.jth.user.RenterHostListingRelationship;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static org.jth.fields.Amenities.*;
import static org.jth.fields.ListingType.*;

public class Main {



  public static void main(String[] args) {
    try {
//      switch (menu()) {
//        case 1:
//          Connection connection = DatabaseDriver.connectingToDatabase();
//          DatabaseDriver.initializeDatabase(connection);
//          DatabaseDriver.dropDatabase(connection);
//          break;
//      }

//      DatabaseDriver databaseDriver = new DatabaseDriver();
//      Connection connection = DatabaseDriver.connectingToDatabase();
//      DatabaseDriver.initializeDatabase(connection);
//      DatabaseDriver.dropDatabase(connection);
//
      DatabaseInsertHelperImpl databaseInsertHelper = new DatabaseInsertHelperImpl();
//      databaseInsertHelper.insertListings(11, 11, "70 TOWN CENTER", "0B2 0P3",
//          APARTMENT, 1, "TORONTO", "CANANDA", true);
//      databaseInsertHelper.insertAmenities(1, KITCHEN);
//      databaseInsertHelper.insertAmenities(1, HEATING);
//      databaseInsertHelper.insertAmenities(1, WASHER);
//      databaseInsertHelper.insertAmenities(1, WIFI);
//
//      databaseInsertHelper.insertUnavailableTimes(1, parseStringToDate("2018-01-08"), parseStringToDate("2018-01-10"));
//      databaseInsertHelper.insertUnavailableTimes(1, parseStringToDate("2018-03-12"), parseStringToDate("2018-03-20"));
//      databaseInsertHelper.insertUnavailableTimes(1, parseStringToDate("2018-07-15"), parseStringToDate("2018-07-20"));
//
//
//      databaseInsertHelper.insertListings(9, 9, "80 TOWN CENTER", "0B2 0P10",
//          BOUNTIQUE_HOTEL, 3, "TORONTO", "CANANDA", true);
//      databaseInsertHelper.insertAmenities(2, IRON);
//      databaseInsertHelper.insertAmenities(2, LAPTOP_FRIENDLY_WORKSPACE);
//      databaseInsertHelper.insertAmenities(2, SELF_CHECK_IN);
//      databaseInsertHelper.insertAmenities(2, CARBON_MONOXIDE_DETECTOR);
//
//      databaseInsertHelper.insertUnavailableTimes(2, parseStringToDate("2018-01-15"), parseStringToDate("2018-01-20"));
//      databaseInsertHelper.insertUnavailableTimes(2, parseStringToDate("2018-02-07"), parseStringToDate("2018-02-10"));
//      databaseInsertHelper.insertUnavailableTimes(2, parseStringToDate("2018-10-15"), parseStringToDate("2018-10-20"));
//
//      databaseInsertHelper.insertListings(10, 10, "100 TOWN CENTER", "0B2 0P1",
//          BOUNTIQUE_HOTEL, 4, "TORONTO", "CANANDA",true);
//      databaseInsertHelper.insertAmenities(3, SHAMPOO);
//      databaseInsertHelper.insertAmenities(3, AIR_CONDITIONING);
//      databaseInsertHelper.insertAmenities(3, DRYER);
//      databaseInsertHelper.insertAmenities(3, BREAKFAST);
//
//      databaseInsertHelper.insertUnavailableTimes(3, parseStringToDate("2018-01-01"), parseStringToDate("2018-01-09"));
//      databaseInsertHelper.insertUnavailableTimes(3, parseStringToDate("2018-09-12"), parseStringToDate("2018-09-20"));
//      databaseInsertHelper.insertUnavailableTimes(3, parseStringToDate("2018-07-01"), parseStringToDate("2018-07-02"));
//
//      databaseInsertHelper.insertListings(10, 11, "110 TOWN CENTER", "0B2 0P6",
//          BOUNTIQUE_HOTEL, 35, "TORONTO", "CANANDA", true);
//      databaseInsertHelper.insertAmenities(4, GANGERS);
//      databaseInsertHelper.insertAmenities(4, HAIR_DRYER);
//      databaseInsertHelper.insertAmenities(4, TV);
//      databaseInsertHelper.insertAmenities(4, HIGH_CHAIR);
//
//      databaseInsertHelper.insertUnavailableTimes(4, parseStringToDate("2018-06-16"), parseStringToDate("2018-06-19"));
//      databaseInsertHelper.insertUnavailableTimes(4, parseStringToDate("2018-11-12"), parseStringToDate("2018-11-20"));
//      databaseInsertHelper.insertUnavailableTimes(4, parseStringToDate("2018-05-17"), parseStringToDate("2018-05-18"));

      /** database search helper**/
      /** test for search listings by data range**/
//      DatabaseSelectHelperImpl databaseSelectHelper = new DatabaseSelectHelperImpl();
//      databaseSelectHelper.selectAllListings(1);
//      ArrayList<Listings> thisListings = databaseSelectHelper.getListings();
//      SearchListings search = new SearchListings();
//      search.getListingsByDataRange(parseStringToDate("2018-06-16"), parseStringToDate("2018-06-19"), thisListings);

      /** test for search listings by post-code**/
//      DatabaseSelectHelperImpl databaseSelectHelper2 = new DatabaseSelectHelperImpl();
//      databaseSelectHelper2.selectAllListings(1);
//      ArrayList<Listings> thisListings2 = databaseSelectHelper2.getListings();
//      SearchListings search2 = new SearchListings();
//      search2.getListingsAmenitiesByPostcode("0B2 0P6",thisListings2);
//
//      for(Listings listings: thisListings2) {
//        System.out.println("***********************");
//        System.out.println(listings.getId());
//        System.out.println(listings.getPrice());
//        System.out.println(listings.getAddress());
//        System.out.println(listings.getAvailability());
//        for(Amenities amenities: listings.getAmenities()) {
//          System.out.println(amenities.name());
//        }
//        for(ArrayList<Date> dates: listings.getUnavailableTime()){
//          for(Date date: dates) {
//            System.out.println(date.toString());
//          }
//        }
//      }

//      databaseInsertHelper.insertHosts(1001,"Xingyuan", "Zhu", "70 Town Center",
//          "M1P 0B2", parseStringToDate("1998-12-01"), "student");
//
//      databaseInsertHelper.insertHosts(1002,"Yining", "Zhou", "190 Brough Dr",
//          "M1P 888", parseStringToDate("2001-5-18"), "student");
//
//      databaseInsertHelper.insertHosts(1003,"Junjie", "Huang", "I Do Know where",
//          "M1P 999", parseStringToDate("1892-12-01"), "student");
//
//      databaseInsertHelper.insertRentors(1004,"Jaja", "Wang", "80 Town center", "888 M0P",
//          parseStringToDate("1999-12-01"), "Car drive", "1003210367", "12-31", 123);
//
//      databaseInsertHelper.insertRentors(1005,"Fafa", "Liang", "90 Town center", "111 M0P",
//          parseStringToDate("1990-12-11"), "drive resr", "1003211111", "12-34", 321);
//
//      databaseInsertHelper.insertRentors(1006,"Tingting", "Bang", "1010 Town center", "000 M0P",
//          parseStringToDate("1999-01-12"), "drive prive", "100320000", "12-03", 893);
//
//      databaseInsertHelper.insertHostOwnListings(1001, 1);
//      databaseInsertHelper.insertHostOwnListings(1002, 2);
//      databaseInsertHelper.insertHostOwnListings(1003, 3);
//      databaseInsertHelper.insertHostOwnListings(1001, 4);
//
//
//      databaseInsertHelper.insertRelationshipRenterHost(1004, 1001, 1);
//      databaseInsertHelper.insertRelationshipRenterHost(1005, 1001, 4);
//      databaseInsertHelper.insertRelationshipRenterHost(1006, 1002, 2);

      databaseInsertHelper.insertCommentListing(1004, 3, "haha", 5);

//      databaseSelectHelper.selectAllUsers(2);
//      ArrayList<Users> hostList = databaseSelectHelper.getUsers();

//      for(Users users: hostList) {
//        System.out.println("***********************");
//        System.out.println(users.getName());
//        for(Integer listing: ((Hosts) users).getOwnListings()){
//          System.out.println(listing);
//        }
//      }

//      databaseSelectHelper.selectRelation();
//      RenterHostListingRelationship renterHostListingRelationship = databaseSelectHelper.getRenterHostListingRelationship();
//      Comment comment = new Comment(1004, 1001, "hahaha", 5, renterHostListingRelationship);


//      DatabaseUpdateHelperImpl databaseUpdateHelper = new DatabaseUpdateHelperImpl();
//      databaseUpdateHelper.updatePrice(1, 12);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static int menu() {
    System.out.println("Welcome!");
    System.out.println("Renter -- 1");
    System.out.println("Host -- 2");
    System.out.println("******************");
    Scanner input = new Scanner(System.in);
    return input.nextInt();
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
