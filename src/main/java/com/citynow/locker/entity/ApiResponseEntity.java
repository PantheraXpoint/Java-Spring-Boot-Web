package com.citynow.locker.entity;

import java.util.List;
import org.springframework.http.HttpStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponseEntity<T> {
  private String message;
  private HttpStatus status;
  private String errorCode;
  private List<T> dataList;

  public ApiResponseEntity(HttpStatus status, List<T> dataList, String message,
      String errorCode) {
    this.status = status;
    this.dataList = dataList;
    this.errorCode = errorCode;
    this.message = message;
  }

  public ApiResponseEntity(HttpStatus status, List<T> dataList, String message) {
    this.status = status;
    this.dataList = dataList;
    this.message = message;
  }
}
