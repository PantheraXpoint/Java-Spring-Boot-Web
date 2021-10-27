package com.citynow.exercise.demo.Object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties({"id"})
public class LockerCard {
    private Integer id;
    private String startDate;
    private String endDate;
    private String remark;
    private Integer user_id;

    LockerCard(){

    }

    LockerCard(Integer id, String startDate, String endDate, String remark, Integer user_id){
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.remark = remark;
        this.user_id = user_id;
    }
}
