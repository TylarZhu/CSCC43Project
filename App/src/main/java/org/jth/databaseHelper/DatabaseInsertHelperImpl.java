package org.jth.databaseHelper;

import org.jth.Fields.Amenities;
import org.jth.Fields.ListingType;

import java.sql.*;

import static org.jth.databaseHelper.DatabaseDriver.*;


public class DatabaseInsertHelperImpl implements DatabaseInsertHelper {

  @Override
  public void insertListings(double latitude, double longitude, String address, String postal_code, ListingType listingType, double price, Amenities amenities) {
    Connection connection = connectingToDatabase();
    PreparedStatement stmt = null;
    ResultSet rs = null;

  }
}
