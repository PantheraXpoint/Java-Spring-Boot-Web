package com.citynow.locker.entity.newDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewCustomerDTO {
  private Integer id;
  private String fullName;
  private Integer type;
  private Integer gender;
  private String phoneNumber;
  private String position;
}
