package org.example.entity;

import lombok.Data;

@Data
public class Volunteer_old {
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String password;
  private String confirmPassword;

 public Volunteer_old(String firstName, String lastName, String email, String phoneNumber, String password, String confirmPassword) {
   this.firstName = firstName;
   this.lastName = lastName;
   this.email = email;
   this.phoneNumber = phoneNumber;
   this.password = password;
   this.confirmPassword = confirmPassword;
 }
}
