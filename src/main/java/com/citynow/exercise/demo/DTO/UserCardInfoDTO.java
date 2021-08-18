package com.citynow.exercise.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCardInfoDTO {
    private Integer id;
    private String email;
    private String password;
    private String fullName;
    private String type;
    private String gender;
    private String phoneNumber;
    private String locker_code;
    private Integer card_id;
    private String locker_name;

}
