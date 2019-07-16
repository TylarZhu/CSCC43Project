package org.jth.search;

import org.jth.databaseHelper.DatabaseSelectHelperImpl;
import org.jth.listings.UnavailableTime;

import java.util.ArrayList;

public class SearchUnavailableTime {
  private ArrayList<UnavailableTime> unavailableTimes = null;
  private DatabaseSelectHelperImpl databaseSelectHelper = new DatabaseSelectHelperImpl();

  public static void main(String[] args) {
    SearchUnavailableTime searchUnavailableTime = new SearchUnavailableTime();
    ArrayList<UnavailableTime> list = searchUnavailableTime.getAllUnavailableTimes();
    for(int i = 0; i < list.size(); i ++) {
      System.out.println(list.get(i).getTime());
    }
  }

  public ArrayList<UnavailableTime> getAllUnavailableTimes() {
    databaseSelectHelper.selectAllUnavailableTime();
    unavailableTimes = databaseSelectHelper.getUnavailableTimes();
    return unavailableTimes;
  }
}
