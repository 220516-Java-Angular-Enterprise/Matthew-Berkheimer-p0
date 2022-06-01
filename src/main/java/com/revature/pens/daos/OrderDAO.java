package com.revature.pens.daos;

import com.revature.pens.models.Order;
import com.revature.pens.models.User;
import com.revature.pens.util.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements CrudDAO<Order>{
    Connection con = DatabaseConnection.getCon();

    //todo redo sql statements for new table
    @Override
    public void save(Order object) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO orders (orderid, storeid, customerid, penid, datesent, datereceived, status, amount) VALUES ((?),(?),(?),(?),(?),(?),(?),(?))");
            ps.setString(1, object.getOrderID());
            ps.setString(2, object.getStoreID());
            ps.setString(3, object.getCustomerID());
            ps.setString(4, object.getPenID());
            ps.setTimestamp(5, Timestamp.valueOf(object.getOrderSent()));
            ps.setTimestamp(6, Timestamp.valueOf(object.getOrderReceived()));
            ps.setString(7, object.getStatus());
            ps.setInt(8, object.getAmount());
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    @Override
    public void update(Order object) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE orders SET datesent = ?, datereceived = ?, status = ?, amount = ? WHERE orderid = ?");
            ps.setTimestamp(1, Timestamp.valueOf(object.getOrderSent()));
            ps.setTimestamp(2, Timestamp.valueOf(object.getOrderReceived()));
            ps.setString(3, object.getStatus());
            ps.setInt(4, object.getAmount());
            ps.setString(5, object.getOrderID());
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    //todo maybe change order to have its own id, would make a lot of things easier
    @Override
    public void delete(String id) {
        try{
            PreparedStatement ps = con.prepareStatement("DELETE FROM orders WHERE orderid = ?");
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    @Override
    public List<Order> getAll() {
        List<Order> orderList = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT o.orderid, storeid, customerid, penid, status, datesent, datereceived, o.amount, p.p_name, u.c_name, s.s_address, s.s_city, s.s_state FROM orders o inner join pens p on p.id = o.penid inner join users u on u.id = o.customerid inner join stores s ON s.id = o.storeid order by o.datesent");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Order order = new Order(rs.getString("orderid"),
                        rs.getString("storeid"),
                        rs.getString("customerid"),
                        rs.getString("penid"),
                        rs.getString("status"),
                        rs.getTimestamp("datesent").toLocalDateTime(),
                        rs.getTimestamp("datereceived").toLocalDateTime(),
                        rs.getInt("amount"));
                order.setPenName(rs.getString("p_name"));
                order.setCustomerName(rs.getString("c_name"));
                order.setStoreAddress(rs.getString("s_address") + ", " + rs.getString("s_city") + ", " + rs.getString("s_state"));
                orderList.add(order);
            }
        }catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return orderList;
    }

    @Override
    public Order getByID(String id) {
        return null;
    }
}
