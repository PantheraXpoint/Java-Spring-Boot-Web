package com.citynow.exercise.demo.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import com.citynow.exercise.demo.DTO.UserCardInfoDTO;
import com.citynow.exercise.demo.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public class AppServiceImplement implements AppService{
    // Select locker_user.id, full_name, email, card.id as card_id, locker.locker_code, locker.locker_name from locker.locker_user,locker.card,locker.locker
    // where locker_user.id in []
}
