package com.revature.pens.daos;

import com.revature.pens.models.Store;
import com.revature.pens.models.User;
import com.revature.pens.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreDAO implements CrudDAO<Store>{
    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(Store object) {
        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO stores (id, s_address, s_city, s_state) VALUES ((?),(?),(?),(?))");
            ps.setString(1, object.getId());
            ps.setString(2, object.getAddress());
            ps.setString(3, object.getCity());
            ps.setString(4, object.getState());
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    @Override
    public void update(Store object) {
        try{
            PreparedStatement ps = con.prepareStatement("UPDATE stores SET s_address = ?, s_city = ?, s_state = ? WHERE id = ?");
            ps.setString(1,object.getAddress());
            ps.setString(2,object.getCity());
            ps.setString(3,object.getState());
            ps.setString(4,object.getId());
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
            PreparedStatement ps = con.prepareStatement("DELETE FROM stores WHERE id = ?");
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    @Override
    public List<Store> getAll() {
        List<Store> storeList= new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM stores");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Store store= new Store(rs.getString("id"), rs.getString("s_address"), rs.getString("s_city"), rs.getString("s_state"));
                storeList.add(store);
            }
        }catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return storeList;
    }

    @Override
    public Store getByID(String id) {
        Store store = new Store();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM stores WHERE id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                store.setId(rs.getString("id"));
                store.setAddress(rs.getString("s_address"));
                store.setCity(rs.getString("s_city"));
                store.setState(rs.getString("s_state"));
            }
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return store;
    }
}
