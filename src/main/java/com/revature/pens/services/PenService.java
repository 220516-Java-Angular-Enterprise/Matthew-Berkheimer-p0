package com.revature.pens.services;

import com.revature.pens.daos.PenDAO;
import com.revature.pens.models.Pen;
import com.revature.pens.util.annotations.Inject;

import java.util.List;

public class PenService {
    @Inject
    private final PenDAO penDAO;

    @Inject
    public PenService(PenDAO penDAO){this.penDAO = penDAO;}

    public void register(Pen pen){
        penDAO.save(pen);
    }

    public void edit(Pen pen){
        penDAO.update(pen);
    }

    public void remove(String id){
        penDAO.delete(id);
    }

    public List<Pen> getPens(){
        return penDAO.getAll();
    }

    public boolean isValidName(String name){
        //todo??
        return true;
    }

    public boolean isValidTipSize(String tipSize){
        //todo
        return true;
    }

    public boolean isValidColor(String color){
        //todo
        return true;
    }

    public boolean isValidDescription(String description){
        //todo ??
        return true;
    }


}
