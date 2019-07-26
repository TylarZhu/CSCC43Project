package org.jth.report;

import org.jth.databaseHelper.DatabaseSelectHelper;
import org.jth.databaseHelper.DatabaseSelectHelperImpl;
import org.jth.listings.Listings;
import org.jth.user.Hosts;
import org.jth.user.Users;

import java.util.ArrayList;
import java.util.HashMap;

public class ReportOfHosts {
    private ArrayList<Users> hosts = new ArrayList<>();

    public HashMap<Integer, String> rankHostsByCity(String city, ArrayList<Listings> listings){
        HashMap<Integer,String> rankMap = new HashMap<>();
        DatabaseSelectHelperImpl select = new DatabaseSelectHelperImpl();
        select.selectAllUsers(2);
        this.hosts = select.getUsers();
        for(Users users : hosts){
            int report = 0;
            ArrayList<Integer> ownListings = ((Hosts)users).getOwnListings();
            for(int i = 0; i < ownListings.size(); i++){
                if(ownListings.get(i) == listings.get(i).getId() && listings.get(i).getCity().equals(city)){
                    report ++;
                }
                rankMap.put(report, users.getName());
            }
        }
        return rankMap;
    }


    public HashMap<Integer,String> rankHostsByCountry(String country, ArrayList<Listings> listings){
        HashMap<Integer,String> rankMap = new HashMap<>();
        DatabaseSelectHelperImpl select = new DatabaseSelectHelperImpl();
        select.selectAllUsers(2);
        this.hosts = select.getUsers();
        for(Users users : hosts){
            int report = 0;
            ArrayList<Integer> ownListings = ((Hosts)users).getOwnListings();
            for(int i = 0; i < ownListings.size(); i++){
                if(ownListings.get(i) == listings.get(i).getId() && listings.get(i).getCountry().equals(country)){
                    report ++;
                }
                rankMap.put(report, users.getName());
            }
        }
        return rankMap;
    }
}
