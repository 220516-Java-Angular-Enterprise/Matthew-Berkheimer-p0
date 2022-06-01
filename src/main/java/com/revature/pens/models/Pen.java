package com.revature.pens.models;

public class Pen {
    //todo set attribute types to right types
    private String id;
    private String name;
    private String tipSize; //Int for size?
    private String color; //Enum for colors?
    private String description;
    private int cost; //todo might need to change costs to double

    public Pen(String id, String name, String tipSize, String color, String description, int cost) {
        this.id = id;
        this.name = name;
        this.tipSize = tipSize;
        this.color = color;
        this.description = description;
        this.cost = cost;
    }

    public String getCostString(){
        //todo
        return "";
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

    public String getTipSize() {
        return tipSize;
    }

    public void setTipSize(String tipSize) {
        this.tipSize = tipSize;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Pen{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", tipSize='" + tipSize + '\'' +
                ", color='" + color + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                '}';
    }
}
