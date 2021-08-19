package com.citynow.exercise.demo.entity;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "locker")
public class LockerEntity {
    @JsonIgnore
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "locker_code", nullable = false, unique = true)
    private String locker_code;

    @ManyToMany(mappedBy = "lockers")
    private List<CardEntity> cards = new ArrayList<>();

    @Column(name = "locker_name")
    private String locker_name;

}
