package org.jth.report;

import org.jth.listings.Listings;
import org.jth.user.Hosts;
import org.jth.user.Users;

import java.util.ArrayList;
import java.util.HashMap;

public class ReportOfHosts {

    public HashMap<Integer, String> rankHostsByCity(String city, ArrayList<Listings> listings, ArrayList<Users> hosts){
        HashMap<Integer,String> rankMap = new HashMap<>();
        for(Users users : hosts){
            int report = 0;
            ArrayList<Integer> ownListings = ((Hosts)users).getOwnListings();
            for(int i = 0; i < ownListings.size(); i++){
                int index = 0;
                for(int j = 0; j < listings.size(); j++){
                    if(listings.get(j).getId() == ownListings.get(i)){
                        index = j;
                    }
                }
                if(listings.get(index).getCity().equals(city)){
                    report ++;
                }
            }
            rankMap.put(report, users.getName());
        }
        return rankMap;
    }


    public HashMap<Integer,String> rankHostsByCountry(String country, ArrayList<Listings> listings,
                                                      ArrayList<Users> hosts){
        HashMap<Integer,String> rankMap = new HashMap<>();
        for(Users users : hosts){
            int report = 0;
            ArrayList<Integer> ownListings = ((Hosts)users).getOwnListings();
            for(int i = 0; i < ownListings.size(); i++){
                int index = 0;
                for(int j = 0; j < listings.size(); j++){
                    if(listings.get(j).getId() == ownListings.get(i)){
                        index = j;
                    }
                }
                if(listings.get(index).getCountry().equals(country)){
                    report ++;
                }
            }
            rankMap.put(report, users.getName());
        }
        return rankMap;
    }

    public ArrayList<Users> rankHostsByPossessionPerCountry(String country, ArrayList<Listings> listings,
                                                  ArrayList<Users> hosts){
        int total = 0;
        for(int i = 0; i < listings.size(); i++){
            if(listings.get(i).getCountry().equals(country)){
                total ++;
            }
        }
        int standard = Math.round(total / 10);
        for(Users user : hosts){
            int possession = 0;
            ArrayList<Integer> ownListings = ((Hosts)user).getOwnListings();
            for(int a = 0; a < ownListings.size(); a++){
                int index = 0;
                for (int j = 0; j < listings.size(); j++){
                    if(listings.get(j).getId() == ownListings.get(a)){
                        index = j;
                    }
                }
                if(listings.get(index).getCountry().equals(country)){
                    possession ++;
                }
            }
            if(possession < standard){
                hosts.remove(user);
            }
        }
        return hosts;
    }

    public ArrayList<Users> rankHostsByPossessionPerCity(String city, ArrayList<Listings> listings,
                                                  ArrayList<Users> hosts){
        int total = 0;
        for(int i = 0; i < listings.size(); i++){
            if(listings.get(i).getCity().equals(city)){
                total ++;
            }
        }
        int standard = Math.round(total / 10);
        for(Users user : hosts){
            int possession = 0;
            ArrayList<Integer> ownListings = ((Hosts)user).getOwnListings();
            for(int a = 0; a < ownListings.size(); a++){
                int pos = 0;
                for (int j = 0; j < listings.size(); j++){
                    if(listings.get(j).getId() == ownListings.get(a)){
                        pos = j;
                    }
                }
                if(listings.get(pos).getCity().equals(city)){
                    possession ++;
                }
            }
            if(possession < standard){
                hosts.remove(user);
            }
        }
        return hosts;
    }
}
