package org.jth.comment;

import org.jth.user.RenterHostListingRelationship;

import java.util.ArrayList;

public class Comment {
  private int id;
  private int fromUsrIns;
  private int toUsrIns;
  private String content;
  private int rate;

  public Comment(int id, int fromUsrIns, int toUsrIns, String content, int rate) {
    this.id = id;
    this.fromUsrIns = fromUsrIns;
    this.toUsrIns = toUsrIns;
    this.content = content;
    this.rate = rate;
  }

  public Comment(int fromUsrIns, int toUsrIns, String content,
                            int rate, RenterHostListingRelationship renterHostListingRelationship) {
    boolean flag = true;
    ArrayList<ArrayList<Integer>> relation = renterHostListingRelationship.getRelation();
    for (ArrayList<Integer> r1: relation) {
      if((fromUsrIns == r1.get(0) && toUsrIns == r1.get(1)) || (fromUsrIns == r1.get(1) && toUsrIns == r1.get(0))){
        this.fromUsrIns = fromUsrIns;
        this.toUsrIns = toUsrIns;
        this.content = content;
        this.rate = rate;
        flag = false;
      }
    }
    if(flag) {
      System.out.println("Cannot insert comment! host and renter does not have any relationship!");
    }
  }
}
