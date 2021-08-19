package com.citynow.exercise.demo.object;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Locker {
    private String locker_code;
    private String locker_name;

    Locker(){

    }

    Locker (String code, String name){
        this.locker_code = code;
        this.locker_name = name;
    }

}
