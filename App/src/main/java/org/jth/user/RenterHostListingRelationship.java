package org.jth.user;

import java.util.ArrayList;

public class RenterHostListingRelationship {
  private int relation_id;
  private ArrayList<ArrayList<Integer>> relation = new ArrayList<>();

  public void addRelationship(int renterIns, int hostIns, int listId) {
    ArrayList<Integer> rela = new ArrayList<>();
    rela.add(renterIns);
    rela.add(hostIns);
    rela.add(listId);
    relation.add(rela);
  }

  public ArrayList<ArrayList<Integer>> getRelation(){
    return relation;
  }


  //TODO renteral history
}
