package com.citynow.exercise.demo.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Entity
@Table(name = "card")
public class CardEntity {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonProperty("startDate")
    @Column(name ="start_date_time")
    private Date startDate;

    @JsonProperty("endDate")
    @Column(name = "end_date_time")
    private Date endDate;

    @Column
    private String remark;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name ="user_id")
    private UserEntity user;

}
