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


  public ArrayList<Integer> getRentalHistory(int renterIns) {
    ArrayList<Integer> listIdArray = new ArrayList<>();
    for(ArrayList<Integer> r: relation) {
      if(r.get(0) == renterIns) {
        listIdArray.add(r.get(2));
      }
    }
    return listIdArray;
  }
}
