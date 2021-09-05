package com.citynow.locker.entity.newDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class newTicketDetailDTO {
  Integer ticketId;

  Integer customerId;

  String fullName;

  Integer type;

  Integer gender;

  String phoneNumber;

  String lockerCode;

  String remark;

  String startDate;

  String endDate;

}
