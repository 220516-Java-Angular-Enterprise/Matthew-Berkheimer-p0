package com.revature.pens.daos;

import com.revature.pens.util.database.DatabaseConnection;

import java.sql.Connection;
import java.util.List;

public interface CrudDAO<T> {



    void save(T object);
    void update(T object);
    void delete(String id);
    List<T> getAll();
    T getByID(String id);
}
