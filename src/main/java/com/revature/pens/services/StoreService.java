package com.revature.pens.services;

import com.revature.pens.daos.StoreDAO;
import com.revature.pens.models.Store;
import com.revature.pens.util.Enums.StateCode;
import com.revature.pens.util.annotations.Inject;

import java.util.List;

public class StoreService {
    @Inject
    private final StoreDAO storeDAO;

    @Inject
    public StoreService(StoreDAO storeDAO){this.storeDAO = storeDAO;}

    public void register(Store store){
        storeDAO.save(store);
    }

    public void editStore(Store store){
        storeDAO.update(store);
    }

    public List<Store> getAllStores(){
        return storeDAO.getAll();
    }

    public void deleteStore(String id){
        storeDAO.delete(id);
    }

    public boolean isValidAddress(String address){
        //todo
        return true;
    }

    public boolean isValidCity(String city){
        return city.matches("^(\\w\\s?)*\\w$");
    }

    public boolean isValidState(String state){
        //todo use lambda expression to compare values to state???
        for (StateCode out:StateCode.values()) {
            if(state.equals(out.toString()))return true;
        }
        return false;
    }
}
