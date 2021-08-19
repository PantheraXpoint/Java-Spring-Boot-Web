package com.citynow.exercise.demo.object;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LockerAssign {
    private Integer card_id;
    private String locker_code;

    LockerAssign(){

    }

    LockerAssign(Integer id, String code){
        this.card_id = id;
        this.locker_code = code;
    }
}
