package org.jth.databaseHelper;

import java.util.Date;

public interface DatabaseCheckDataHelper {
  boolean checkUserOrListExsits(int ins, int choice);
  boolean checkCommentTarget(int fromIns, int toIns, int choice);
  boolean checkHostOwnList(int ins, int id);
  boolean checkListingStatus(int id);
  boolean checkListingRenterRelation(int list_id, int renterIns);
  boolean checkFullRelationShipExsits(int renterIns, int hostIns, int listId);
  boolean checkCommentExsits(int fromIns, int toIns);
  boolean checkCommentListingExsits(int fromIns, int list_id);
  boolean checkFutureBooking(int renterIns, int listId);
  boolean checkUnavailableTimes(int list_id, Date fromDate, Date toDate);
}
