package org.jth.listings;

import java.util.Date;

public class UnavailableTime {
  private int id;
  private int list_id;
  private Date time;

  public UnavailableTime(int id, int list_id, Date time){
    this.id = id;
    this.list_id = list_id;
    this.time = time;
  }

  public int getListId() {
    return list_id;
  }

  public Date getTime() {
    return time;
  }
}
