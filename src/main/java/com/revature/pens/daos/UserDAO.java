package com.revature.pens.daos;

import com.revature.pens.models.User;
import com.revature.pens.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements CrudDAO<User>{
    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(User object) {
        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (id, username, password, role, email, c_name, c_address, c_phone, c_cctoken) VALUES ((?),(?),(?),(?),(?),(?),(?),(?),(?))");
            ps.setString(1, object.getId());
            ps.setString(2, object.getUsername());
            ps.setString(3, object.getPassword());
            ps.setString(4, object.getRole());
            ps.setString(5, object.getEmail());
            ps.setString(6, object.getName());
            ps.setString(7, object.getAddress());
            ps.setString(8, object.getPhone());
            ps.setString(9, object.getCcToken());
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    @Override
    public void update(User object) {
        try{
            PreparedStatement ps = con.prepareStatement("UPDATE users SET username = ?, password = ?, role = ?, email = ?, c_name = ?, c_address = ?, c_phone = ?, c_cctoken = ? WHERE id = ?");
            ps.setString(1,object.getUsername());
            ps.setString(2,object.getPassword());
            ps.setString(3,object.getRole());
            ps.setString(4,object.getEmail());
            ps.setString(5, object.getName());
            ps.setString(6, object.getAddress());
            ps.setString(7, object.getPhone());
            ps.setString(8, object.getCcToken());
            ps.setString(9,object.getId());
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }

    }

    @Override
    public void delete(String id) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM users WHERE id = ?");
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }

    }

    @Override
    public List<User> getAll() {
        List<User> userList= new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                User user = new User(rs.getString("id"), rs.getString("username"),rs.getString("password"),rs.getString("role"),rs.getString("email"), rs.getString("c_name"), rs.getString("c_address"), rs.getString("c_phone"), rs.getString("c_cctoken"));
                userList.add(user);
            }
        }catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return userList;
    }

    @Override
    public User getByID(String id) {
        return null;
    }
}
