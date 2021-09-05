package com.citynow.locker.entity.newDto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewCustomerAssign {
  private LocalDate startDateTime;
  private Integer customerId;
  private String fullName;
  private Integer gender;
  private String phoneNumber;
  private String lockerCode;
  private String remark;
}
