package com.revature.pens.daos;

import com.revature.pens.models.Pen;
import com.revature.pens.models.User;
import com.revature.pens.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PenDAO implements CrudDAO<Pen>{
    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(Pen object) {
        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO pens (id, p_name, tipsize, color, description, cost) VALUES ((?),(?),(?),(?),(?),(?))");
            ps.setString(1, object.getId());
            ps.setString(2, object.getName());
            ps.setString(3, object.getTipSize());
            ps.setString(4, object.getColor());
            ps.setString(5, object.getDescription());
            ps.setInt(6, object.getCost());
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    @Override
    public void update(Pen object) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE customers SET p_name = ?, tipsize = ?, color = ?, description = ?, cost = ? WHERE id = ?");
            ps.setString(1, object.getName());
            ps.setString(2, object.getTipSize());
            ps.setString(3, object.getColor());
            ps.setString(4, object.getDescription());
            ps.setInt(5, object.getCost());
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
            PreparedStatement ps = con.prepareStatement("DELETE FROM pens WHERE id = ?");
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    @Override
    public List<Pen> getAll() {
        List<Pen> penList= new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM pens");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Pen pen = new Pen(rs.getString("id"),
                        rs.getString("p_name"),
                        rs.getString("tipsize"),
                        rs.getString("color"),
                        rs.getString("description"),
                        rs.getInt("cost"));
                penList.add(pen);
            }
        }catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return penList;
    }

    @Override
    public Pen getByID(String id) {
        return null;
    }
}
