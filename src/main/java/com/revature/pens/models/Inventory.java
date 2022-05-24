package com.revature.pens.models;

public class Inventory {
    private String penID;
    private String storeID;
    private int amount;

    public Inventory(String penID, String storeID, int amount) {
        this.penID = penID;
        this.storeID = storeID;
        this.amount = amount;
    }

    public String getPenID() {
        return penID;
    }

    public void setPenID(String penID) {
        this.penID = penID;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "penID='" + penID + '\'' +
                ", storeID='" + storeID + '\'' +
                ", amount=" + amount +
                '}';
    }
}
