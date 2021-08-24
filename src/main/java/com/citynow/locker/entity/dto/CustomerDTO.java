package com.citynow.locker.entity.dto;


import lombok.Data;

public interface CustomerDTO {

  Integer getId();

  String getFullName();

  Integer getType();

  Integer getGender();

  String getPhoneNumber();

  String getPosition();
}

