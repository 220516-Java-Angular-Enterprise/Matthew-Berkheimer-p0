package com.revature.pens.models;

import java.time.LocalDateTime;

public class Order {
    private String storeID;
    private String customerID;
    private String penID;
    private String status; //todo maybe use enumerator here
    private LocalDateTime orderSent;
    private LocalDateTime orderReceived;
    private int amount;
    //todo might need to add cost in case pen price changes (ie sale)
    //private double cost; //In case the product price is changed


    public Order(String storeID, String customerID, String penID, String status, LocalDateTime orderSent, LocalDateTime orderReceived, int amount) {
        this.storeID = storeID;
        this.customerID = customerID;
        this.penID = penID;
        this.status = status;
        this.orderSent = orderSent;
        this.orderReceived = orderReceived;
        this.amount = amount;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getPenID() {
        return penID;
    }

    public void setPenID(String penID) {
        this.penID = penID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getOrderSent() {
        return orderSent;
    }

    public void setOrderSent(LocalDateTime orderSent) {
        this.orderSent = orderSent;
    }

    public LocalDateTime getOrderReceived() {
        return orderReceived;
    }

    public void setOrderReceived(LocalDateTime orderReceived) {
        this.orderReceived = orderReceived;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "storeID='" + storeID + '\'' +
                ", customerID='" + customerID + '\'' +
                ", penID='" + penID + '\'' +
                ", status='" + status + '\'' +
                ", orderSent=" + orderSent +
                ", orderReceived=" + orderReceived +
                ", amount=" + amount +
                '}';
    }
}
