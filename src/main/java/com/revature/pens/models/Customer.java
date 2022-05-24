package com.revature.pens.models;

import java.util.List;

public class Customer {
    //todo look up credit card token;
    private String id;
    private String name;
    private String address;
    private String email;
    private String phone;
    //private String ccToken; //Credit Card Token
    private List<Orders> orderHistory;
    private String userID; //Foreign id for SQL database


}
