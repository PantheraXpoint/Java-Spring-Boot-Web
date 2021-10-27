package com.citynow.exercise.demo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.citynow.exercise.demo.Entity.CardEntity;
import com.citynow.exercise.demo.Entity.UserEntity;
import com.citynow.exercise.demo.Repository.CardRepository;
import com.citynow.exercise.demo.Repository.UserRepository;
import com.citynow.exercise.demo.Service.AppService;
import com.citynow.exercise.demo.Service.AppServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class AppServiceTest {
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CardRepository cardRepository;

    @InjectMocks
    private AppServiceImpl appService;

    @Test
    public void testCreateNewUser(){
        UserEntity testing = new UserEntity();
        testing.setEmail("quang@gmail.com");
        testing.setFullName("");
        testing.setGender("male");
        testing.setPassword("");
        testing.setPhoneNumber("090");
        testing.setType("manager");
        Mockito.when(userRepository.save(testing)).thenReturn(null);
        boolean result = appService.checkValidUser(testing);
        assertTrue(result);
    }

    @Test
    public void testNonNullUserLists(){
        List<UserEntity> testing = new ArrayList<>();
        List<CardEntity> cards = new ArrayList<>();
        testing.add(new UserEntity(1,"a","b","c","d","e","f",cards));
        Mockito.when(userRepository.findAll()).thenReturn(testing);
        Assertions.assertFalse(appService.alertReturnNullUser(testing));
    }

    @Test
    public void testNonNullCardLists(){
        List<CardEntity> testing = new ArrayList<>();
        UserEntity temp = new UserEntity();
        testing.add(new CardEntity(1,new Date(2019,9,9),new Date(2121,8,8),"c",temp));
        Mockito.when(cardRepository.findAll()).thenReturn(testing);
        Assertions.assertFalse(appService.alertReturnNullCard(testing));
    }
    
}
