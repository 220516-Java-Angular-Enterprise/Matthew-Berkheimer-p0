package com.revature.pens.daos;

import com.revature.pens.models.Customer;
import com.revature.pens.models.User;
import com.revature.pens.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements CrudDAO<Customer>{
    Connection con = DatabaseConnection.getCon();

    //todo probably merge customer and user and add boolean for if user
    @Override
    public void save(Customer object) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO customers (id, c_name, c_address, c_phone, c_cctoken, userid) VALUES ((?),(?),(?),(?),(?),(?))");
            ps.setString(1, object.getId());
            ps.setString(2, object.getName());
            ps.setString(3, object.getAddress());
            ps.setString(4, object.getPhone());
            ps.setString(5, object.getCcToken());
            ps.setString(6, object.getUserID());
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    @Override
    public void update(Customer object) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE customers SET c_name = ?, c_address = ?, c_phone = ?, c_cctoken = ?, userid = ? WHERE id = ?");
            ps.setString(1, object.getName());
            ps.setString(2, object.getAddress());
            ps.setString(3, object.getPhone());
            ps.setString(4, object.getCcToken());
            ps.setString(5, object.getUserID());
            ps.setString(6, object.getId());
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }

    }

    @Override
    public void delete(String id) {
        try{
            PreparedStatement ps = con.prepareStatement("DELETE FROM customers WHERE id = ?");
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }

    }

    //todo make this a join request with user data as well? or combine user and customer data together
    @Override
    public List<Customer> getAll() {
        List<Customer> customerList = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM customers");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Customer customer = new Customer(rs.getString("id"), rs.getString("c_name"), rs.getString("c_address"), rs.getString("c_phone"), rs.getString("c_cctoken"), rs.getString("userid"));
                customerList.add(customer);
            }
        }catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return customerList;
    }

    @Override
    public Customer getByID(String id) {
        return null;
    }
}
