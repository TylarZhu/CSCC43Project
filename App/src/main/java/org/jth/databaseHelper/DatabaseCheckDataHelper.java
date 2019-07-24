package org.jth.databaseHelper;

public interface DatabaseCheckDataHelper {
  boolean checkUserOrListExsits(int ins, int choice);
  boolean checkCommentTarget(int fromIns, int toIns, int choice);
  boolean checkHostOwnList(int ins, int listId);
}
