package org.jth.report;

import org.jth.listings.Listings;
import org.jth.user.RenterHostListingRelationship;
import org.jth.user.Users;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ReportOfRenters {

    public HashMap<Integer,String> rankRentersByBookingPeriodic(Date from, Date to, ArrayList<Users> renters,
                                                                ArrayList<Listings> listings){
        HashMap<Integer, String> rankMap = new HashMap<>();
        for(Users users : renters){
            int report = 0;
            RenterHostListingRelationship get = new RenterHostListingRelationship();
            ArrayList<Integer> history = get.getRentalHistory(users.getSocial_insurance_number());
            for(int i = 0; i < history.size(); i++){
                for(int j = 0; j < listings.size(); j++){
                    if(history.get(i) == listings.get(j).getId()){
                        if(!listings.get(j).checkBooking(from, to)){
                            report++;
                        }
                    }
                }
            }
            rankMap.put(report, users.getName());
        }
        return rankMap;
    }

    public HashMap<Integer,String> rankRentersPerCity(Date from, Date to, ArrayList<Users> renters,
                                                      ArrayList<Listings> listings, String city){
        HashMap<Integer,String> rankMap = new HashMap<>();
        for(Users users : renters){
            int report = 0;
            RenterHostListingRelationship get = new RenterHostListingRelationship();
            ArrayList<Integer> history = get.getRentalHistory(users.getSocial_insurance_number());
            for(int i = 0; i < history.size(); i++){
                for(int j = 0; j < listings.size(); j++){
                    Listings current = listings.get(j);
                    if(history.get(i) == current.getId()){
                        if(current.getCity().equals(city) &&
                        ! current.checkBooking(from, to)){
                            report++;
                        }
                    }
                }
            }
            rankMap.put(report, users.getName());
        }
        return  rankMap;
    }
}
