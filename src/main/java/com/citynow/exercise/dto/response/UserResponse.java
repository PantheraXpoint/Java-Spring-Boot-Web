package com.citynow.exercise.dto.response;

import com.citynow.exercise.constant.GenderConst;
import com.citynow.exercise.constant.TypeConst;
import com.citynow.exercise.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Tran Ngoc Nhan
 * @since 2020-08-22
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class UserResponse {

  private Integer id;
  private String email;
  private String password;
  private String fullName;
  private String type;
  private String gender;
  private String phoneNumber;
  private ErrorResponse errorResponse;

  public UserResponse(UserEntity userEntity) {
    this.id = userEntity.getId();
    this.email = userEntity.getEmail();
    this.password = userEntity.getPassword();
    this.fullName = userEntity.getFullName();
    this.type = TypeConst.ID_BY_TYPE.get(userEntity.getType());
    this.gender = GenderConst.ID_BY_Gender.get(userEntity.getGender());
    this.phoneNumber = userEntity.getPhoneNumber();
  }
}
