package org.jth.report;

import org.jth.listings.Listings;

import java.util.ArrayList;

public class ReportOfListings {
    public int getReportByCountory(ArrayList<Listings> list, String country){
        int report = 0;
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getCountry().equals(country)){
                report ++;
            }
        }
        return report;
    }
    public int getReportByCountoryAndCity(ArrayList<Listings> list, String country, String city){
        int report = 0;
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getCountry().equals(country) && list.get(i).getCity().equals(city)){
                report ++;
            }
        }
        return report;
    }
    public int getReportByCountoryAndCityAndZip(ArrayList<Listings> list, String country, String city, String post){
        int report = 0;
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getCountry().equals(country)
            && list.get(i).getCity().equals(city)
            && list.get(i).getPostal_code().equals(post)){
                report ++;
            }
        }
        return report;
    }

}
