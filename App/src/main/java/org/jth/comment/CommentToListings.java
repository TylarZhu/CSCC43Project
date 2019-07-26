package org.jth.comment;

import org.jth.user.RenterHostListingRelationship;

import java.util.ArrayList;

public class CommentToListings {
  private int commentId;
  private int userIns;
  private int listId;
  private String content;
  private int rate;

  public CommentToListings(int commentId, int userIns, int listId, String content, int rate){
    this.commentId = commentId;
    this.userIns = userIns;
    this.listId = listId;
    this.content = content;
    this.rate = rate;
  }

  public CommentToListings(int userIns, int listId, String content,
                           int rate, RenterHostListingRelationship renterHostListingRelationship) {
    ArrayList<ArrayList<Integer>> relation = renterHostListingRelationship.getRelation();
    for (ArrayList<Integer> r1: relation) {
      if(r1.get(0) == userIns && r1.get(2) == listId) {
        this.userIns = userIns;
        this.listId = listId;
        this.content = content;
        this.rate = rate;
      }
    }
  }
}
