package com.citynow.exercise.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Tran Ngoc Nhan
 * @since 2020-08-22
 */
@Getter
@Setter
public class ErrorResponse {

  private String message;
  private String errorCode;

}
