package com.citynow.locker.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class NewCustomerDTO {
    private String fullName;
    private Integer gender;
}
