package com.citynow.locker.entity.dto;

public interface TicketDetailDTO {
  Integer getTicketId();

  Integer getCustomerId();

  String getFullName();

  Integer getType();

  Integer getGender();

  String getPhoneNumber();

  String getLockerCode();

  String getRemark();

  String getStartDateTime();

  String getEndDateTime();
}
