package com.revature.pens.services;

import com.revature.pens.daos.CustomerDAO;
import com.revature.pens.models.Customer;
import com.revature.pens.util.annotations.Inject;

public class CustomerService {
    @Inject
    private final CustomerDAO customerDAO;

    @Inject
    public CustomerService(CustomerDAO customerDAO){this.customerDAO = customerDAO;}

    public void register(Customer customer){
        customerDAO.save(customer);
    }

    public boolean isValidAddress(String address){
        //todo
        return true;
    }

    public boolean isValidPhone(String phone){
        //todo
        return true;
    }

    public boolean isValidCreditCard(String creditCard){
        //todo
        return true;
    }

    public boolean isValidName(String name){
        //todo???
        return true;
    }
}
