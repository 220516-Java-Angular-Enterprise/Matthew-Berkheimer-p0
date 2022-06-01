package com.revature.pens;

import com.revature.pens.daos.UserDAO;
import com.revature.pens.models.Order;
import com.revature.pens.models.Pen;
import com.revature.pens.services.UserService;
import com.revature.pens.ui.LoginMenu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MainDriver {
    //todo two menus one that can manipulate the inventory and one that can buy from the inventory
    //todo people can have infinite money

    public static void main(String args[]){
        UserService userService = new UserService(new UserDAO());
        new LoginMenu(userService).start();

        List<Order> otherList = new ArrayList<>();

        List<Order> orderList = otherList.stream().filter(order -> order.getCustomerID().equals("insert id")).collect(Collectors.toList());
        orderList.forEach(System.out::println);

        List<Order> orderList1 = otherList.stream().sorted(Comparator.comparing(Order::getOrderSent)).collect(Collectors.toList());
        orderList1.forEach(System.out::println);

        List<Pen> pen = new ArrayList<>();
        List<String> cost = pen.stream().map(pen1 -> "$" + String.valueOf(pen1.getCost())).collect(Collectors.toList());
    }
}