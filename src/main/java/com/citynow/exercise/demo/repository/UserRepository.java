package com.citynow.exercise.demo.repository;

import java.util.List;

import com.citynow.exercise.demo.DTO.UserCardInfoDTO;
import com.citynow.exercise.demo.DTO.UserDTO;
import com.citynow.exercise.demo.DTO.UserInfoDTO;
import com.citynow.exercise.demo.DTO.UserLockerDTO;
import com.citynow.exercise.demo.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity,Integer>{
    @Query(nativeQuery=true,value = 
    "select  u.id as id, full_name as fullName, email as email,ca.id as cardId, l.locker_code as lockerCode ,l.locker_name as lockerName from public.user  as u, card as ca, "
    +"locker as l, locker_assign as la where u.id in (:input)" 
    +"and u.id=ca.user_id and ca.id=la.card_id and la.locker_code=l.locker_code"
       )
    public List<UserCardInfoDTO> firstQuery(@Param("input")List<Integer> listIds);

    @Query(nativeQuery = true, value = "select public.user.id as id,public.user.email as email,public.user.full_name as fullName from public.user "
    +"where public.user.full_name "
    +"LIKE (%:name%);")
    public List<UserDTO> secondQuery(@Param("name")String name);

    @Query(nativeQuery=true,value = "SELECT id, full_name, type, case when public.user.full_name LIKE '%Quan' then 'CTO' when public.user.full_name LIKE '%Tai' then 'CEO' else 'member' END as position FROM public.user;")
    public List<UserInfoDTO> thirdQuery();

    @Query(nativeQuery = true, value = "SELECT ca.user_id as userId,u.full_name as fullName,ca.start_date_time as startDateTime ,"
    +"la.locker_code as lockerCode,la.card_id as cardId,"
	+"ROW_NUMBER() OVER("
    +"PARTITION BY ca.user_id"
    +"ORDER BY ca.user_id) as no"
    +"from public.card as ca, public.user as u, public.locker_assign as la"
    +"where u.id = ca.user_id and ca.id = la.card_id);")
    public List<UserLockerDTO> fourthQuery();
}
