package org.jth.report;

import java.util.ArrayList;
import java.util.Date;
import org.jth.listings.Listings;

public class ReportOfBooking {
    public int totalNumberOfBookingByDateRange(Date from, Date to, String city, ArrayList<Listings> listings){
        int report = 0;
        for(int i = 0; i < listings.size(); i++){
            if (listings.get(i).checkBooking(from, to) && listings.get(i).getCity().equals(city)){
                report ++;
            }
        }
        return report;
    }

    public int totalNumberOfBookingByZipCode(String post, String city, ArrayList<Listings> listings){
        int report = 0;
        for(int i = 0; i < listings.size(); i++){
            if(listings.get(i).getCity().equals(city) && listings.get(i).getPostal_code().equals(post)){
                report++;
            }
        }
        return report;
    }
}
