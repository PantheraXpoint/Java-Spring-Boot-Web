package com.citynow.exercise.demo.Converter;

import com.citynow.exercise.demo.Entity.UserEntity;
import com.citynow.exercise.demo.Object.LockerUser;

import org.springframework.stereotype.Component;

@Component
public class Converter {
    public UserEntity toEntity(LockerUser lockerUser){
        UserEntity entity = new UserEntity();
        entity.setEmail(lockerUser.getEmail());
        entity.setPassword(lockerUser.getPassword());
        entity.setFullName(lockerUser.getFullName());
        entity.setType(lockerUser.getType());
        entity.setGender(lockerUser.getGender());
        entity.setPhoneNumber(lockerUser.getPhoneNumber());
        return entity;
    }

    public LockerUser toObject(UserEntity userEntity){
        LockerUser user = new LockerUser();
        user.setEmail(userEntity.getEmail());
        user.setPassword(userEntity.getPassword());
        user.setFullName(userEntity.getFullName());
        user.setType(userEntity.getType());
        user.setGender(userEntity.getGender());
        user.setPhoneNumber(userEntity.getPhoneNumber());
        return user;
    }
}
