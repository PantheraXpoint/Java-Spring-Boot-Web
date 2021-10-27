package com.citynow.exercise.demo.Repository;

import com.citynow.exercise.demo.Entity.CardEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<CardEntity,Integer>{
    
}
