package org.jth.user;

import java.util.Date;

public class Users {
  protected int social_insurance_number;
  protected String first_name;
  protected String last_name;
  protected String address;
  protected String postal_code;
  protected Date date_of_birth;
  protected String occupation;

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

  public String getName(){
    return (this.first_name + " " + this.last_name);
  }
}
