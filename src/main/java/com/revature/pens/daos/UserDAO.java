package com.revature.pens.daos;

import com.revature.pens.models.User;
import com.revature.pens.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDAO implements CrudDAO<User>{
    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(User object) {
        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (id, username, password, role) VALUES (?,?,?,?)");
            ps.setString(1, object.getId());
            ps.setString(2, object.getUsername());
            ps.setString(3, object.getPassword());
            ps.setString(4, object.getRole());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException("An error has occurred when trying to connect to the database.");
        }
    }

    @Override
    public void update(User object) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getByID(String id) {
        return null;
    }
}
