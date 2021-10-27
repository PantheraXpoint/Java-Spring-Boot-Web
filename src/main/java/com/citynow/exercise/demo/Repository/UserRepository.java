package com.citynow.exercise.demo.Repository;



import com.citynow.exercise.demo.Entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Integer>{
    
}
