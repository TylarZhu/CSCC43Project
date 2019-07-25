package org.jth.databaseHelper;

public interface DatabaseDeleteHelper {
  void deleteListingById(int ins, int id);
  void deleteUserByIns(int ins);
  void cancelBooking(int ins, int id);
}
