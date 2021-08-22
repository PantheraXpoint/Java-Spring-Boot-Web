package com.citynow.exercise.service;

import java.util.List;
import com.citynow.exercise.entity.UserEntity;
import com.citynow.exercise.constant.ActionModeConst;
import com.citynow.exercise.dto.response.UserResponse;

/**
 * @author Tran Ngoc Nhan
 * @since 2020-08-22
 */
public interface UserService {

  void initUserData();

  List<UserResponse> getAll();

  UserResponse getById(String id);

  UserResponse saveOrUpdate(UserEntity userEntity, ActionModeConst mode);

}
