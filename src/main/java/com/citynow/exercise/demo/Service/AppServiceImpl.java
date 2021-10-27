package com.citynow.exercise.demo.Service;

import java.util.List;

import com.citynow.exercise.demo.Entity.CardEntity;
import com.citynow.exercise.demo.Entity.UserEntity;
import com.citynow.exercise.demo.Repository.CardRepository;
import com.citynow.exercise.demo.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AppServiceImpl implements AppService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardRepository cardRepository;

    public boolean checkValidUser(UserEntity lockerUser){
        if (lockerUser.getEmail().equals(null) || lockerUser.getEmail().equals("")){
            return false;
        }
        else if (lockerUser.getPassword().equals(null) || lockerUser.getPassword().equals("")){
            return false;
        }
        return true;
    }

    public boolean alertReturnNullUser(List<UserEntity> temp){
        // List<UserEntity> temp= userRepository.findAll();
        return temp.isEmpty();
    }
    public boolean alertReturnNullCard(List<CardEntity> all){
        return all.isEmpty();
    }
    @Override
    public UserEntity save(UserEntity lockerUser) {
        // TODO Auto-generated method stub
        // UserEntity userEntity = newConverter.toEntity(lockerUser);

        UserEntity userEntity = userRepository.save(lockerUser);
        // System.out.println(userEntity.getId());
        // return newConverter.toObject(userEntity);
        return userEntity;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        // TODO Auto-generated method stub
        List<UserEntity> allUsers = userRepository.findAll();
        try{
            if (alertReturnNullUser(allUsers)){
                throw new Exception("No value available");
            }
            return allUsers;
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        // return allUsers;
        return null;
    }

    @Override
    public List<CardEntity> getAllCards() {
        // TODO Auto-generated method stub
        List<CardEntity> allCards = cardRepository.findAll();
        try{
            if (alertReturnNullCard(allCards)){
                throw new Exception("No value available");
            }
            return allCards;
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        // return allUsers;
        return null;
    }

    
}
