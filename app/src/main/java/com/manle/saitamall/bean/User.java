package com.manle.saitamall.bean;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/9.
 */

public class User  implements Serializable{

    private static final long serialVersionUID = 1299203321698333044L;
    private Long id;
    private String userName;
    private String phone;
    private String password;
    private String figure;
    private Float point;


    public User(String userName, String phone, String password, String figure, Float point) {
        this.userName = userName;
        this.phone = phone;
        this.password = password;
        this.figure = figure;
        this.point = point;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public Float getPoint() {
        return point;
    }

    public void setPoint(Float point) {
        this.point = point;
    }
}