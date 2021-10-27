package com.citynow.exercise.demo.Service;

import java.util.List;

import com.citynow.exercise.demo.Entity.CardEntity;
import com.citynow.exercise.demo.Entity.UserEntity;

public interface AppService {
    UserEntity save (UserEntity lockerUser);
    List<UserEntity> getAllUsers();
    List<CardEntity> getAllCards();
}
