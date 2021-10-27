package com.citynow.exercise.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

import com.citynow.exercise.demo.Entity.CardEntity;
import com.citynow.exercise.demo.Entity.UserEntity;
import com.citynow.exercise.demo.Object.LockerUser;
import com.citynow.exercise.demo.Service.AppService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class AppController {

    @Autowired
    private AppService appService;

    @PostMapping("/user/create")
    public UserEntity createUser(@RequestBody UserEntity user){
        return appService.save(user);
    }

    @PostMapping("/user/created")
    public String createdUser(@RequestBody UserEntity user){
        return String.valueOf(appService.save(user));
    }

    @PutMapping("/user/update")
    public LockerUser updateUser(@RequestBody LockerUser user) {
        //TODO: process PUT request
        
        return user;
    }

    @DeleteMapping("/user/delete")
    public void deleteUser(@RequestBody Integer[] ids){

    }

    @GetMapping("/user/all")
    public List<UserEntity> getAllUsers() {
        return appService.getAllUsers();
    }

    @GetMapping("/card/all")
    public List<CardEntity> getAllCards() {
        return appService.getAllCards();
    }
    
}

