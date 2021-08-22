package com.citynow.exercise.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Tran Ngoc Nhan
 * @since 2020-08-22
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

  private Integer id;
  private String email;
  private String password;
  private String fullName;
  private Integer type;
  private Integer gender;
  private String phoneNumber;
}
