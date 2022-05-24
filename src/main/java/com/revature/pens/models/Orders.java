package com.revature.pens.models;

import java.time.LocalDateTime;

public class Orders {
    private String id;
    private String name; //todo is this necessary?
    private String description;
    private String status; //todo maybe use enumerator here
    private LocalDateTime orderDate;
    private double cost; //In case the product price is changed
    //private String customerID; // is another possibility
    //private String productID; // maybe
}
