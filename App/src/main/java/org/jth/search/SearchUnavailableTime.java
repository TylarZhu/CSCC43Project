package org.jth.search;

import org.jth.databaseHelper.DatabaseSelectHelperImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SearchUnavailableTime {
  private DatabaseSelectHelperImpl databaseSelectHelper = new DatabaseSelectHelperImpl();

//  public static void main(String[] args) {
//    SearchUnavailableTime searchUnavailableTime = new SearchUnavailableTime();
//    ArrayList<Integer> list = searchUnavailableTime.getAvailableListingByDateRange(
//        searchUnavailableTime.parseStringToDate("2019-07-12"), searchUnavailableTime.parseStringToDate("2019-07-29"));
//    for(int i = 0; i < list.size(); i ++) {
//      System.out.println(list.get(i));
//    }
//  }

  public Date parseStringToDate(String date) {
    try {
      return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    } catch (ParseException e) {
      System.out.println("Parse date error! see below details: ");
      e.printStackTrace();
      return null;
    }
  }

//  public ArrayList<Integer> getAvailableListingByDateRange(Date From, Date to) {
//    if(unavailableTimes == null) {
//      getAllUnavailableTimes();
//    }
//    ArrayList<Integer> afterFiltListings = new ArrayList<>();
//    for (UnavailableTime unavailableTime:unavailableTimes) {
//      if(!(unavailableTime.getTime().after(From) && unavailableTime.getTime().before(to))) {
//        afterFiltListings.add(unavailableTime.getListId());
//      }
//    }
//    return afterFiltListings;
//  }
}
