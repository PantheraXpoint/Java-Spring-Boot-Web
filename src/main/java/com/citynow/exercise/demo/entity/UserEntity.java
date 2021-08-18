package com.citynow.exercise.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "locker_user",schema = "locker")
public class UserEntity {
    @JsonIgnore
    @Id
    // @SequenceGenerator(name = "userID", sequenceName = "userID", allocationSize = 1)
    // @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "userID")
    @Column(name = "id", nullable = false, unique = true,columnDefinition = "serial",updatable = false)
    private Integer id;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    @JsonProperty("fullName")
    private String fullName;

    @Column(name = "type")
    private String type;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone_number")
    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<CardEntity> cards = new ArrayList<>();
}

