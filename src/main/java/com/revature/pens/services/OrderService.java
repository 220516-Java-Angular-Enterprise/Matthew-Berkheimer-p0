package com.revature.pens.services;

import com.revature.pens.daos.OrderDAO;
import com.revature.pens.models.Order;
import com.revature.pens.util.annotations.Inject;

import java.util.List;
import java.util.stream.Collectors;

public class OrderService {
    @Inject
    private final OrderDAO orderDAO;

    @Inject
    public OrderService(OrderDAO orderDAO){this.orderDAO = orderDAO;}

    public void register(Order order){
        orderDAO.save(order);
    }

    public void update(Order order){
        orderDAO.update(order);
    }

    public List<Order> getCustomerOrders(String id){
        List<Order> otherList = orderDAO.getAll();
        List<Order> orderList = otherList.stream().filter(order -> order.getCustomerID().equals(id)).collect(Collectors.toList());
        return orderList;
    }

    public List<Order> getStoreOrders(String id){
        List<Order> otherList = orderDAO.getAll();
        List<Order> orderList = otherList.stream().filter(order -> order.getStoreID().equals(id)).collect(Collectors.toList());
        return orderList;
    }
}
