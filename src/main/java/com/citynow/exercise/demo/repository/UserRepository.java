package com.citynow.exercise.demo.repository;

import java.util.List;

import com.citynow.exercise.demo.DTO.UserCardInfoDTO;
import com.citynow.exercise.demo.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity,Integer>{
    @Query(nativeQuery = true,value = 
    "select  u.id, full_name, email,ca.id as card_id, l.locker_code,l.locker_name from locker_user  as u, card as ca, "
    +"locker as l, locker_assign as la where u.id in (:input)" 
    +"and u.id=ca.user_id and ca.id=la.card_id and la.locker_code=l.locker_code"
       )
    public List<UserCardInfoDTO> test(@Param("input")List<Integer> listIds);
}
