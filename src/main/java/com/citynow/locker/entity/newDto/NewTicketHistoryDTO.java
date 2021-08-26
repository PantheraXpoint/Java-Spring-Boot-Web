package com.citynow.locker.entity.newDto;

import java.security.Timestamp;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewTicketHistoryDTO {
  private Integer ticketId;
  private Integer customerId;
  private String fullName;
  private String lockerCode;
  private String lockerName;
  private LocalDate startDateTime;
  private LocalDate endDateTime;
}
