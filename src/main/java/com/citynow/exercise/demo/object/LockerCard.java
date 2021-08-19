package com.citynow.exercise.demo.object;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
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
