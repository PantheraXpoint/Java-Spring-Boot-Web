package com.citynow.exercise.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.citynow.exercise.demo.DTO.UserCardInfoDTO;
import com.citynow.exercise.demo.repository.UserRepository;
import com.citynow.exercise.demo.service.AppService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/all")
    public void getAllUsers(){
        List<Integer> listIds = new ArrayList<>(Arrays.asList(1,2,3));
        List <UserCardInfoDTO> temp = userRepository.test(listIds);
        temp.forEach(System.out::println);
    }

}
