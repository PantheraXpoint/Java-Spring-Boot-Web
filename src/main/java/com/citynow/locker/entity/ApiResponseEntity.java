package com.citynow.locker.entity;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponseEntity<T> {
  private String errorMessage;
  private String errorCode;
  private List<T> dataList;

  public ApiResponseEntity(List<T> dataList, String errorMessage, String errorCode) {
    this.dataList = dataList;
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }
}
