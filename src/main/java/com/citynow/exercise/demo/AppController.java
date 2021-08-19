package com.citynow.exercise.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.citynow.exercise.demo.DTO.UserCardInfoDTO;
import com.citynow.exercise.demo.DTO.UserDTO;
import com.citynow.exercise.demo.DTO.UserInfoDTO;
import com.citynow.exercise.demo.DTO.UserLockerDTO;
import com.citynow.exercise.demo.repository.UserRepository;
import com.citynow.exercise.demo.service.AppService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/all")
    public List<UserCardInfoDTO> getAllUsers(){
        List<Integer> listIds = new ArrayList<>(Arrays.asList(1,2,3));
        List <UserCardInfoDTO> temp = userRepository.firstQuery(listIds);
        // temp.forEach(System.out::println);
        return temp;
    }

    @GetMapping("/user")
    public List<UserDTO> getUserByName(@RequestParam("fullName")String fullName){
        List<UserDTO> temp = userRepository.secondQuery(fullName);
        System.out.println(temp);
        return temp;
    }

    @GetMapping("/user/position")
    public List<UserInfoDTO> getUserPosition(){
        List <UserInfoDTO> temp = userRepository.thirdQuery();
        // temp.forEach(System.out::println);
        return temp;
    }

    @GetMapping("/user/requirement")
    public List<UserLockerDTO> getByRequirement(){
        List <UserLockerDTO> temp = userRepository.fourthQuery();
        return temp;
    }
}
