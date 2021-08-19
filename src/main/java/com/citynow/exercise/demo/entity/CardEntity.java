package com.citynow.exercise.demo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "card")
public class CardEntity {
    @JsonIgnore
    @Id
    // @SequenceGenerator(name = "cardID", sequenceName = "cardID", allocationSize = 1)
    // @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "userID")
    @Column(name = "id", nullable = false, unique = true,columnDefinition = "serial",updatable = false)
    private Integer id;

    @ManyToMany
    @JoinTable(name = "locker_assign", joinColumns = @JoinColumn(name = "card_id"), inverseJoinColumns = @JoinColumn(name = "locker_locker_code"))
    private List<LockerEntity> lockers = new ArrayList<>();

    @JsonProperty("startDate")
    @Column(name ="start_date_time")
    private Date startDate;

    @JsonProperty("endDate")
    @Column(name = "end_date_time")
    private Date endDate;

    @Column(name = "remark")
    private String remark;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name ="user_id")
    private UserEntity user;

}
