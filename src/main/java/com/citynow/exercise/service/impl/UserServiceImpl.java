package com.citynow.exercise.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.citynow.exercise.constant.ActionModeConst;
import com.citynow.exercise.constant.GenderConst;
import com.citynow.exercise.constant.TypeConst;
import com.citynow.exercise.dto.response.ErrorResponse;
import com.citynow.exercise.dto.response.UserResponse;
import com.citynow.exercise.entity.UserEntity;
import com.citynow.exercise.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author Tran Ngoc Nhan
 * @since 2020-08-22
 */
@Service
public class UserServiceImpl implements UserService {

  private List<UserEntity> userEntities = new ArrayList<>();

  @Override
  public void initUserData() {

    UserEntity shenLong =
        new UserEntity(1, "black-tiger@citynow.com", "123456", "Shen Long", 1, 0, "090");
    UserEntity anotherLong = new UserEntity(2, "tiger@citynow.com", "234561", "Long", 0, 1, "090");
    UserEntity yugo = new UserEntity(3, "wolf@citynow.com", "345612", "Yugo", 0, 1, "090");
    UserEntity allice = new UserEntity(4, "rabbit@citynow.com", "456123", "Alice", 0, 1, "090");
    UserEntity gado = new UserEntity(5, "lion@citynow.com", "561234", "Gado", 0, 1, "090");
    UserEntity bakuryuu = new UserEntity(6, "mole@citynow.com", "612345", "Bakuryuu", 0, 1, "090");

    userEntities.add(shenLong);
    userEntities.add(anotherLong);
    userEntities.add(yugo);
    userEntities.add(allice);
    userEntities.add(gado);
    userEntities.add(bakuryuu);
  }

  @Override
  public List<UserResponse> getAll() {
    List<UserResponse> userResponses = new ArrayList<>();

    this.userEntities.forEach(user -> {
      userResponses.add(new UserResponse(user));
    });

    return userResponses;
  }

  @Override
  public UserResponse getById(String id) {
    try {

      UserEntity user = null;
      Integer index = Integer.valueOf(id);
      for (UserEntity userEntity : this.userEntities) {

        if (userEntity.getId().equals(index)) {
          user = userEntity;
          break;
        }
      }

      return new UserResponse(user);
    } catch (NumberFormatException | NullPointerException e) {

      ErrorResponse errorResponse = new ErrorResponse()
          .setErrorCode(HttpStatus.NOT_FOUND.name())
          .setMessage(
              e instanceof NumberFormatException
                  ? "Id must be a number"
                  : "Not found id: " + id);
      return new UserResponse().setErrorResponse(errorResponse);
    }
  }

  @Override
  public UserResponse saveOrUpdate(UserEntity userEntity, ActionModeConst mode) {

    ErrorResponse errorResponse = new ErrorResponse();

    if (TypeConst.notContain(userEntity.getType()) ||
        GenderConst.notContain(userEntity.getGender())) {
      errorResponse.setErrorCode(HttpStatus.NOT_FOUND.name());
      errorResponse.setMessage("Invalid Type or Gender");
      return new UserResponse().setErrorResponse(errorResponse);
    }

    UserResponse odlUserResponse = this.getById(String.valueOf(userEntity.getId()));

    if (mode.equals(ActionModeConst.INSERT)) {

      if (odlUserResponse.getErrorResponse() == null) {
        errorResponse.setErrorCode(HttpStatus.FOUND.name());
        errorResponse.setMessage("ID already exists");
        return new UserResponse().setErrorResponse(errorResponse);
      }

      int index = this.userEntities.get(this.userEntities.size() - 1).getId() + 1;
      userEntity.setId(index);
      this.userEntities.add(userEntity);
    } else {

      if (odlUserResponse.getErrorResponse() != null) {
        return odlUserResponse;
      }

      for (int i = 0; i < this.userEntities.size(); i++) {

        UserEntity user = this.userEntities.get(i);

        if (user.getId().equals(userEntity.getId())) {
          this.userEntities.set(i, userEntity);
          break;
        }
      }
    }

    return new UserResponse(userEntity);
  }

}
