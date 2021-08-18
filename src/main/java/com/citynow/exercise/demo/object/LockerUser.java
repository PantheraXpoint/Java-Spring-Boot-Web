package com.citynow.exercise.demo.object;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LockerUser {
    private Integer id;
    private String email;
    private String password;
    private String fullName;
    private String type;
    private String gender;
    private String phoneNumber;

    public LockerUser(Integer id, String email, String password, String fullname, String type, String gender,
      String phoneNumber) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.fullName = fullname;
    this.type = type;
    this.gender = gender;
    this.phoneNumber = phoneNumber;
  }



  public LockerUser() {
  }
}
