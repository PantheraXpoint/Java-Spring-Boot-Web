package com.citynow.locker.entity.dto;

import java.sql.Timestamp;

public interface TicketHistoryDTO {
  Integer getTicketId();

  Integer getCustomerId();

  String getFullName();

  String getLockerCode();

  String getLockerName();

  Timestamp getStartDateTime();

  Timestamp getEndDateTime();


}
