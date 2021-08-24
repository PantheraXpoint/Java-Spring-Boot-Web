package com.citynow.locker.entity.dto;

import java.sql.Timestamp;

public interface TicketDetailDTO {
  Integer getTicketId();

  Integer getCustomerId();

  String getFullName();

  Integer getType();

  Integer getGender();

  String getPhoneNumber();

  String getLockerCode();

  String getRemark();

  Timestamp getStartDateTime();

  Timestamp getEndDateTime();
}