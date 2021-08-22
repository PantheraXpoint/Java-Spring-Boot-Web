package com.citynow.exercise.controller;

import java.util.List;
import com.citynow.exercise.constant.ActionModeConst;
import com.citynow.exercise.dto.response.ErrorResponse;
import com.citynow.exercise.dto.response.UserResponse;
import com.citynow.exercise.entity.UserEntity;
import com.citynow.exercise.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tran Ngoc Nhan
 * @since 2020-08-22
 */
@RestController
@RequestMapping("user")
public class UserController {

  private final UserService userService;

  UserController(UserService userService) {
    this.userService = userService;
    userService.initUserData();
  }

  @GetMapping("all")
  public List<UserResponse> all() {
    return this.userService.getAll();
  }

  @GetMapping("detail/{id}")
  public UserResponse detail(@PathVariable String id) {
    return this.userService.getById(id);
  }

  @PostMapping("create")
  public UserResponse create(@RequestBody UserEntity userEntity) {
    return this.userService.saveOrUpdate(userEntity, ActionModeConst.INSERT);
  }

  @PostMapping("update")
  public UserResponse update(@RequestBody UserEntity userEntity) {
    return this.userService.saveOrUpdate(userEntity, ActionModeConst.UPDATE);
  }

  @ExceptionHandler(RuntimeException.class)
  public ErrorResponse handleConstraintViolation() {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
    errorResponse.setMessage("Invalid request!");
    return errorResponse;
  }
}
