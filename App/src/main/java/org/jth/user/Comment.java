package org.jth.user;

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

  public void insertComment(int fromUsrIns, int toUsrIns, String content, int rate) {
    if()
  }
}
