package com.revature.pens;

import com.revature.pens.services.UserService;
import com.revature.pens.ui.LoginMenu;

public class MainDriver {
    //todo two menus one that can manipulate the inventory and one that can buy from the inventory
    //todo hook up to database
    //todo people can have infinite money

    //todo will probably want to set all models using lists to instead use foreign id keys instead

    public static void main(String args[]){
        UserService userService = new UserService();
        new LoginMenu(userService).start();
    }
}
