package com.revature.pens.models;

import java.util.List;

public class Customer {
    //todo look up credit card token;
    private String id;
    private String name;
    private String address;
    private String phone;
    //private String ccToken; //Credit Card Token
    private String userID; //Foreign id for SQL database

    public Customer(String id, String name, String address, String phone, String userID) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.userID = userID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", userID='" + userID + '\'' +
                '}';
    }
}
