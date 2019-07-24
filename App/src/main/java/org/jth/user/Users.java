package org.jth.user;

import java.util.Date;

public class Users {
  private int social_insurance_number;
  private String first_name;
  private String last_name;
  private String address;
  private String postal_code;
  private Date date_of_birth;
  private String occupation;

  public Users(int social_insurance_number, String first_name, String last_name, String address, String postal_code,
               Date date_of_birth, String occupation) {
    this.social_insurance_number = social_insurance_number;
    this.first_name = first_name;
    this.last_name = last_name;
    this.address = address;
    this.postal_code = postal_code;
    this.date_of_birth = date_of_birth;
    this.occupation = occupation;
  }
}
