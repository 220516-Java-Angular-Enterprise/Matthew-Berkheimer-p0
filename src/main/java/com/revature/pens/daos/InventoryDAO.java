package com.revature.pens.daos;

import com.revature.pens.models.Inventory;
import com.revature.pens.models.Pen;
import com.revature.pens.models.Store;
import com.revature.pens.util.custom_exceptions.DatabaseAccessException;
import com.revature.pens.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO implements CrudDAO<Inventory>{
    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(Inventory object) {
        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO inventories (penid, storeid, amount) VALUES ((?),(?),(?))");
            ps.setString(1, object.getPenID());
            ps.setString(2, object.getStoreID());
            ps.setInt(3, object.getAmount());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new DatabaseAccessException("Unable to save Inventory in database. " + LocalDateTime.now() + " " + e.getMessage());
        }
    }

    @Override
    public void update(Inventory object) {
        try{
            PreparedStatement ps = con.prepareStatement("UPDATE inventories SET amount = ? WHERE penid = ? AND storeid = ?");
            ps.setInt(1,object.getAmount());
            ps.setString(2,object.getPenID());
            ps.setString(3,object.getStoreID());
            ps.executeUpdate();
        } catch (SQLException e){
            throw new DatabaseAccessException("Unable to update Inventory in database. " + LocalDateTime.now() + " " + e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        //not used primary key is composite
    }

    public void delete(String storeID, String penID) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM inventories WHERE penid = ? AND storeid = ?");
            ps.setString(1, penID);
            ps.setString(2, storeID);
            ps.executeUpdate();
        } catch (SQLException e){
            throw new DatabaseAccessException("Unable to delete Inventory in database. " + LocalDateTime.now() + " " + e.getMessage());
        }
    }

    @Override
    public List<Inventory> getAll() {
        List<Inventory> inventoryList= new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("select penid, storeid, amount, p.p_name, p.tipsize, p.color, p.\"cost\", s.s_address, s.s_city, s.s_state from inventories i inner join pens p on p.id = i.penid inner join stores s on s.id = i.storeid order by storeid");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String penID = rs.getString("penid");
                String storeID = rs.getString("storeid");
                Inventory inventory = new Inventory(penID,storeID, rs.getInt("amount"));
                inventory.setPen(new Pen(penID,rs.getString("p_name"), rs.getString("tipsize"), rs.getString("color"), "Description here",rs.getInt("cost")));
                inventory.setStore(new Store(storeID, rs.getString("s_address"), rs.getString("s_city"), rs.getString("s_state")));
                inventoryList.add(inventory);
            }
        }catch (SQLException e) {
            throw new DatabaseAccessException("Unable to access all Inventory in database. " + LocalDateTime.now() + " " + e.getMessage());
        }
        return inventoryList;
    }

    //todo change this so it can take both penid and storeid
    @Override
    public Inventory getByID(String id) {
        Inventory inventory = new Inventory();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM stores WHERE id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                inventory.setPenID(rs.getString("penid"));
                inventory.setStoreID(rs.getString("storeid"));
                inventory.setAmount(rs.getInt("amount"));
            }
        } catch (SQLException e){
            throw new DatabaseAccessException("Unable to access Inventory by id in database. " + LocalDateTime.now() + " " + e.getMessage());
        }
        return inventory;
    }
}
