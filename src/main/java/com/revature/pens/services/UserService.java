package com.revature.pens.services;

import com.revature.pens.daos.UserDAO;
import com.revature.pens.models.User;
import com.revature.pens.util.annotations.Inject;

import java.util.List;

public class UserService {
    @Inject
    private final UserDAO userDAO;

    @Inject
    public UserService(UserDAO userDAO){this.userDAO = userDAO;}


    public void register(User user){
        userDAO.save(user);
    }

    public void edit(User user){
        userDAO.update(user);
    }

    public List<User> getAllUser(){
        return userDAO.getAll();
    }

    public void removeUser(User user){
        userDAO.delete(user.getId());
    }

    public List<User> checkUser(){
        return userDAO.getAll();
    }

    public boolean isValidUsername(String username) {
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }

    public boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
    }

    public boolean isValidEmail(String email){
        return email.matches("^([\\w][\\-\\_\\.]?)*\\w@([\\w+]\\-?)*\\w\\.\\w+$");
    }
}
