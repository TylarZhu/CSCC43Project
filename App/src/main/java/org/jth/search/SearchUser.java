package org.jth.search;

import org.jth.databaseHelper.DatabaseSelectHelper;
import org.jth.databaseHelper.DatabaseSelectHelperImpl;
import org.jth.user.Users;

import java.util.ArrayList;

public class SearchUser {
  private ArrayList<Users> renterLists = null;

  public static void main(String[] args) {
    DatabaseSelectHelperImpl databaseSelectHelperImpl = new DatabaseSelectHelperImpl();
    databaseSelectHelperImpl.selectAllRenters();
    ArrayList<Users> test = databaseSelectHelperImpl.getRenterLists();
    for(int i = 0; i < test.size(); i ++) {
      System.out.println(test.get(i).getName());
    }
  }
}
