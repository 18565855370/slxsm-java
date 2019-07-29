package com.slxsm.mybatis.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Team implements Serializable {

    private int id;
    private String username;
    private int age;
    private String address;
}
