package com.citynow.locker.entity.newDto;

import lombok.Data;

@Data
public class newTicketDetailDTO {
  Integer ticketId;

  String fullName;

  Integer gender;

  String phoneNumber;

  String lockerCode;

  String remark;

  String endDate;

}
