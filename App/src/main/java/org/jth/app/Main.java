package org.jth.app;

import org.jth.comment.CommentToListings;
import org.jth.databaseHelper.*;
import org.jth.comment.Comment;
import org.jth.fields.Amenities;
import org.jth.fields.ListingType;
import org.jth.listings.Listings;
import org.jth.user.Hosts;
import org.jth.user.RenterHostListingRelationship;
import org.jth.user.Renters;
import org.jth.user.Users;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import static org.jth.fields.Amenities.*;
import static org.jth.fields.ListingType.*;

public class Main {

  private static Scanner input = new Scanner(System.in);
  private static ArrayList<Users> renterList = new ArrayList<>();
  private static ArrayList<Listings> listings = new ArrayList<>();
  private static ArrayList<Users> hostList = new ArrayList<>();
  private static ArrayList<Comment> comments = new ArrayList<>();
  private static ArrayList<CommentToListings> commentToListings = new ArrayList<>();

  private static DatabaseInsertHelperImpl databaseInsertHelper = new DatabaseInsertHelperImpl();
  private static DatabaseSelectHelperImpl databaseSelectHelper = new DatabaseSelectHelperImpl();
  private static SaveIntoDatabase saveIntoDatabase = new SaveIntoDatabase();
  private static RenterHostListingRelationship renterHostListingRelationship = new RenterHostListingRelationship();

  private static Date today = null;

  public static void main(String[] args) {
    try {
      Connection connection = DatabaseDriver.connectingToDatabase();
      //DatabaseDriver.dropDatabase(connection);
      DatabaseDriver.initializeDatabase(connection);
      initializeApp();
      System.out.println("Please enter today's date in this form (yyyy-MM-dd): ");
      today = parseStringToDate(input.nextLine());
      int choice = menu();
      while(choice != 3) {
        switch (choice) {
          case 1:
            renterMenu();
            break;
          case 2:
            hostMenu();
            break;
        }
        choice = menu();
      }
      saveToDatabase(connection);
      connection.close();

//      DatabaseDriver databaseDriver = new DatabaseDriver();
//      Connection connection = DatabaseDriver.connectingToDatabase();
//     DatabaseDriver.initializeDatabase(connection);
//      DatabaseDriver.dropDatabase(connection);

//      DatabaseInsertHelperImpl databaseInsertHelper = new DatabaseInsertHelperImpl();
//      int listid = databaseInsertHelper.insertListings(11, 11, "70 TOWN CENTER", "0B2 0P3",
//          APARTMENT, 1, "TORONTO", "CANANDA", true);
//      databaseInsertHelper.insertAmenities(1, KITCHEN);
//      databaseInsertHelper.insertAmenities(1, HEATING);
//      databaseInsertHelper.insertAmenities(1, WASHER);
//      databaseInsertHelper.insertAmenities(1, WIFI);
//
//      System.out.println(listid);
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

//      databaseInsertHelper.insertCommentListing(1004, 3, "haha", 5);

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

  private static void initializeApp(){
    databaseSelectHelper.selectAllUsers(1);
    ArrayList<Users> temp = databaseSelectHelper.getRenters();
    renterList.addAll(temp);
    temp.clear();

    databaseSelectHelper.selectAllUsers(2);
    temp = databaseSelectHelper.getHosts();
    hostList.addAll(temp);
    temp.clear();

    databaseSelectHelper.selectAllListings(2);
    ArrayList<Listings> temp2 = databaseSelectHelper.getListings();
    listings.addAll(temp2);
    temp2.clear();

    databaseSelectHelper.selectAllComment();
    ArrayList<Comment> temp3 = databaseSelectHelper.getComments();
    comments.addAll(temp3);
    temp3.clear();

    databaseSelectHelper.selectAllCommentListings();
    ArrayList<CommentToListings> temp4 = databaseSelectHelper.getCommentToListings();
    commentToListings.addAll(temp4);
    temp4.clear();
  }

  private static int menu() {
    System.out.println("Welcome!");
    System.out.println("Renter -- 1");
    System.out.println("Host -- 2");
    System.out.println("Quit -- 3");
    System.out.println("******************");
    return input.nextInt();
  }

  private static void renterMenu() {
    System.out.println("Log in -- 1");
    System.out.println("Sign up -- 2");
    if(input.nextInt() == 1){
      renterLogIn();
    } else {
      renterRegisterMenu();
    }
  }

  private static void renterLogIn(){
    System.out.println("Please enter your social insurance number:");
    int ins = input.nextInt();
    for (Users renter: renterList) {
      if(renter.getSocial_insurance_number() == ins) {
        System.out.println("Log in success!");
        int choice = renterAfterLogInMenu();
        while(choice != 4) {
          switch (choice) {
            case 1:
              renterBooking(ins);
              break;
            case 2:
              // TODO
          }
          choice = renterAfterLogInMenu();
        }
        return;
      }
    }
    System.out.println("Log In failed!");
  }

  private static int renterAfterLogInMenu(){
    System.out.println("rent a house -- 1");
    System.out.println("Comment on a house -- 2");
    System.out.println("View all comments -- 3");
    System.out.println("Quit -- 4");
    return input.nextInt();
  }

  private static void renterRegisterMenu(){
    System.out.println("Please enter your social insurance number:");
    int ins = input.nextInt();
    String empty = input.nextLine();
    System.out.println("Please enter your first name:");
    String firstName = input.nextLine();
    System.out.println("Please enter your last name:");
    String lastName = input.nextLine();
    System.out.println("Please enter your address:");
    String address = input.nextLine();
    System.out.println("Please enter postal code:");
    String postalCode = input.nextLine();
    System.out.println("Please enter your birthday in this form (yyyy-mm-dd):");
    String birthday = input.nextLine();
    System.out.println("Please enter your occupation:");
    String occupation = input.nextLine();
    System.out.println("Please enter your card number:");
    String cardNumber = input.nextLine();
    System.out.println("Please enter your card expiry date in this form (yy/mm):");
    String card_expiry_date = input.nextLine();
    System.out.println("Please enter your cvv:");
    int cvv = input.nextInt();
    renterList.add(new Renters(ins, firstName, lastName, address, postalCode, parseStringToDate(birthday),
        occupation, cardNumber, card_expiry_date, cvv));
    System.out.println("Save renter success!");
    System.out.println("Do you want to rent a house? yes -- 1 no -- 2");
    if(input.nextInt() == 1) {
      renterBooking(ins);
    }
  }

  private static void renterBooking(int renterIns) {
    int choice = viewListingsMenu();
    while(choice != 7) {
      switch (choice) {
        case 1:
          System.out.println("price decrease -- 1 price increase -- 2");
          databaseSelectHelper.selectAllListings(input.nextInt());
          printListings(databaseSelectHelper.getListings());
          break;
        case 2:
          System.out.println("Please enter latitude:");
          double latitude = input.nextDouble();
          System.out.println("Please enter longitude:");
          double longitude = input.nextDouble();
          System.out.println("Please enter distance:");
          double distance = input.nextDouble();
          databaseSelectHelper.selectListingsByLatitudeLongitude(latitude, longitude, distance);
          printListings(databaseSelectHelper.getListings());
          break;
        case 3:
          System.out.println("Please enter postal code:");
          String empty = input.nextLine();
          String postalCode = input.nextLine();
          System.out.println("price decrease -- 1 price increase -- 2");
          databaseSelectHelper.selectListingsByPostalCode(postalCode, input.nextInt());
          printListings(databaseSelectHelper.getListings());
          break;
        case 4:
          System.out.println("Please enter address:");
          empty = input.nextLine();
          String address = input.nextLine();
          System.out.println("price decrease -- 1 price increase -- 2");
          databaseSelectHelper.selectListingsByAddress(address, input.nextInt());
          printListings(databaseSelectHelper.getListings());
          break;
        case 5:
          System.out.println("Please enter range from:");
          double from = input.nextDouble();
          System.out.println("Please enter range to:");
          double to = input.nextDouble();
          databaseSelectHelper.selectListingsByPriceRange(from, to);
          printListings(databaseSelectHelper.getListings());
          break;
        case 6:
          System.out.println("Please choice a list id:");
          int listId = input.nextInt();
          System.out.println("Please enter the date you want to start booking in this form (yyyy-mm-dd):");
          empty = input.nextLine();
          Date startDate = parseStringToDate(input.nextLine());
          System.out.println("Please enter the date you want to end in this form (yyyy-mm-dd):");
          Date endDate = parseStringToDate(input.nextLine());
          booking(listId, startDate, endDate, renterIns);
          break;
      }
      choice = viewListingsMenu();
    }
  }

  private static void booking(int listId, Date start, Date end, int renterIns) {
    for(Listings listing: listings) {
      if(listing.getId() == listId) {
        listing.setUnavailableTime(start, end);
        System.out.println("Booking success!");
        addRenterHostListingRel(listing.getId(), renterIns);
        return;
      }
    }
    System.out.println("Cannot find list id!");
  }

  private static void addRenterHostListingRel(int listId, int renterIns) {
    boolean flag = false;
    for(Users host: hostList) {
      Hosts hosts = (Hosts) host;
      for(Integer list_id: hosts.getOwnListings()){
        if(list_id == listId) {
          renterHostListingRelationship.addRelationship(renterIns,host.getSocial_insurance_number(),listId);
          flag = true;
          break;
        }
      }
      if(flag) {
        break;
      }
    }
  }

  private static int viewListingsMenu(){
    System.out.println("View all listing -- 1");
    System.out.println("Search listing by latitude longitude and distance -- 2");
    System.out.println("Search listing by postal code -- 3");
    System.out.println("Search listing by address -- 4");
    System.out.println("Search listing by price range -- 5");
    System.out.println("Choice a listing to book -- 6");
    System.out.println("Quit -- 7");
    System.out.println("Please enter your choice:");
    return input.nextInt();
  }

  private static void printListings(ArrayList<Listings> listingList) {
    System.out.println("***********************");
    for(Listings listing: listingList) {
      listing.printInfo();
    }
  }

  private static void hostMenu() {
    System.out.println("Log in -- 1");
    System.out.println("Sign up -- 2");
    if(input.nextInt() == 1){
      hostLogIn();
    } else {
      hostRegisterMenu();
    }
  }

  private static void hostLogIn(){
    System.out.println("Please enter your social insurance number:");
    int ins = input.nextInt();
    for(Users host: hostList) {
      if(host.getSocial_insurance_number() == ins) {
        System.out.println("Log in success!");
        System.out.println("Do you want to add new listings? yes -- 1 no -- 2");
        int choice = input.nextInt();
        while (choice == 1) {
          listingsRegisterMenu((Hosts) host);
          System.out.println("Do you want to add new listings? yes -- 1 no -- 2");
          choice = input.nextInt();
        }
        return;
      }
    }
    System.out.println("Log In failed!");
  }

  private static void hostRegisterMenu() {
    Date birthday = checkAge();
    if(birthday != null) {
      System.out.println("Please enter your social insurance number:");
      int ins = input.nextInt();
      System.out.println("Please enter your first name:");
      String empty = input.nextLine();
      String firstName = input.nextLine();
      System.out.println("Please enter your last name:");
      String lastName = input.nextLine();
      System.out.println("Please enter your address:");
      String address = input.nextLine();
      System.out.println("Please enter postal code:");
      String postalCode = input.nextLine();
      System.out.println("Please enter your occupation:");
      String occupation = input.nextLine();
      Hosts host = new Hosts(ins, firstName, lastName, address, postalCode, birthday, occupation);
      hostList.add(host);
      System.out.println("Save host success!");

      System.out.println("Do you want to add new listings? yes -- 1 no -- 2");
      int choice = input.nextInt();
      while (choice == 1) {
        listingsRegisterMenu(host);
        System.out.println("Do you want to add new listings? yes -- 1 no -- 2");
        choice = input.nextInt();
      }
    }
  }

  private static Date checkAge() {
    System.out.println("Please enter your birthday in this form (yyyy-mm-dd):");
    String empty = input.nextLine();
    String birthday = input.nextLine();
    Date date = parseStringToDate(birthday);
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    int birthdayYear = calendar.get(calendar.YEAR);
    calendar.setTime(today);
    int age = calendar.get(calendar.YEAR) - birthdayYear;
    if(age > 18) {
      return date;
    }
    System.out.println("Too young to be a host!");
    return null;
  }


  private static void listingsRegisterMenu(Hosts hosts) {
    System.out.println("Please enter listing's latitude:");
    int latitude = input.nextInt();
    System.out.println("Please enter listing's longitude:");
    int longitude = input.nextInt();
    System.out.println("Please enter listing's address:");
    String empty = input.nextLine();
    String address = input.nextLine();
    System.out.println("Please enter listing's postal code:");
    String postalCode = input.nextLine();
    System.out.println("Please choice a listing's type");
    System.out.println("Please enter the exact letter provided (all words in capital letters and underscore):");
    ListingType listingType = ListingType.valueOf(printListingType());
    System.out.println("Please enter listing's price:");
    double price = input.nextDouble();
    System.out.println("Please enter listing's city:");
    empty = input.nextLine();
    String city = input.nextLine();
    System.out.println("Please enter listing's country:");
    String country = input.nextLine();
    int listId = databaseInsertHelper.insertListings(latitude, longitude, address, postalCode, listingType, price, city, country, true);
    Listings listing = new Listings(listId, latitude, longitude, address, postalCode, listingType, price, city, country, true);
    listings.add(listing);

    printAmenities(listing);

    hosts.setOwnListings(listId);
    System.out.println("Save list success!");
  }

  private static String printListingType() {
    for(ListingType listingType: ListingType.values()) {
      System.out.println(listingType.name());
    }
    return input.nextLine();
  }

  private static void printAmenities(Listings listing) {
    System.out.println("Please choice a amenities's type");
    System.out.println("Enter QUIT for quit.");
    System.out.println("Please enter the exact letter provided (all words in capital letters and underscore):");
    String choice = "";
    for (Amenities amenities : Amenities.values()) {
      System.out.println(amenities.name());
    }
    System.out.println("QUIT");
    while(!choice.equals("QUIT")) {
      choice = input.nextLine();
      if(!choice.equals("QUIT")){
        listing.addAmenities(Amenities.valueOf(choice));
      }
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

  private static void saveToDatabase(Connection connection){
//    DatabaseDriver.dropDatabase(connection);
//    DatabaseDriver.initializeDatabase(connection);
    saveIntoDatabase.saveUser(renterList, 1);
    saveIntoDatabase.saveUser(hostList, 2);
    saveIntoDatabase.saveRenterHostListing(renterHostListingRelationship);
    saveIntoDatabase.saveAmenities(listings);
  }

}
