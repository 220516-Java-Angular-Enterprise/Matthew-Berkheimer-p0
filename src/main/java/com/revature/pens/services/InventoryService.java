package com.revature.pens.services;

import com.revature.pens.daos.InventoryDAO;
import com.revature.pens.models.Inventory;
import com.revature.pens.models.Order;
import com.revature.pens.util.annotations.Inject;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryService {
    @Inject
    private final InventoryDAO inventoryDAO;

    @Inject
    public InventoryService(InventoryDAO inventoryDAO){this.inventoryDAO = inventoryDAO;}

    public void register(Inventory inventory){
        inventoryDAO.save(inventory);
    }

    public void update(Inventory inventory){
        inventoryDAO.update(inventory);
    }

    public List<Inventory> getAllInventories(){
        return inventoryDAO.getAll();
    }

    public void delete(String storeID, String penID){
        inventoryDAO.delete(storeID, penID);
    }

    public boolean isValidStoreID(){
        return true;
    }

    public boolean isValidPenID(){
        return true;
    }

    public List<Inventory> getInventoriesByStore(String id){
        List<Inventory> otherList = inventoryDAO.getAll();
        List<Inventory> inventoryList = otherList.stream().filter(order -> order.getStoreID().equals(id)).collect(Collectors.toList());
        return inventoryList;
    }
}
