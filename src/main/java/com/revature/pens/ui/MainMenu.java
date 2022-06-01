package com.revature.pens.ui;

import com.revature.pens.daos.*;
import com.revature.pens.models.User;
import com.revature.pens.services.*;
import com.revature.pens.util.annotations.Inject;
import com.revature.pens.util.custom_scanner.QuitScanner;
import com.revature.pens.util.helper.UIHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainMenu implements IMenu{
    @Inject
    private final UIHelper uiHelper;
    @Inject
    private final User user;
    private List<String> menu = new ArrayList<>();

    public MainMenu(User user, UIHelper uiHelper){
        this.user = user;
        this.uiHelper = uiHelper;
    }
    //todo
    @Override
    public void start() {
        exit:
        while(true) {
            System.out.println();
            QuitScanner quitScanner = QuitScanner.getInstance();
            menu = Arrays.stream(new String[]{"Edit Profile", "Cart", "Shop", "Users"}).collect(Collectors.toList());
            uiHelper.header("Welcome To Pens " + user.getUsername());
            uiHelper.comLineList(menu);
            UIHelper uiHel= new UIHelper();
            OrderService orderService = new OrderService(new OrderDAO());
                switch (quitScanner.nextLine()) {
                    case "1":
                        UserService userService = new UserService(new UserDAO());
                        new EditProfileMenu(user, userService, uiHel).start();
                        break;
                    case "2":
                        new CartMenu(user, orderService, uiHel).start();
                        break;
                    case "3":
                        StoreService storeService = new StoreService(new StoreDAO());
                        PenService penService = new PenService(new PenDAO());
                        InventoryService inventoryService = new InventoryService(new InventoryDAO());
                        new StoreMenu(user, storeService, uiHel, penService, orderService, inventoryService).start();
                        break;
                    case "4":
                        UserService userService1 = new UserService(new UserDAO());
                        new UserManMenu(userService1, uiHelper, user).start();
                    case "x":
                        break exit;
                    default:
                        System.out.println("\nInvalid Input");
                        break;
                }
        }

        /*todo enable connection for users
        *  cart menu
        *  replace valid boolean checks with try exception throw checks
        *  remove report and report message from customer table and create as its own table
        *  store menu (for shopping) (extra extra reviews can be placed on a store)
        *  extra allow reviews to be placed on products that a customer has ordered/ also possibly on any product in the store
        *  create new Exception for services
        *  enable connection for Managers
        *  when a customer adds an order to their cart when they already have an exact same order(ie same store,customer,and pen id) it should increment the amount in the order by one (before the customer checks out the order)
        *  pen menu where new pens can be added and created (extra: order history and reviews can be seen)
        *  store menu where new stores can be added and edited and store inventory and order history (extra: reviews for each store can be seen)
        *  extra create a report menu that managers can access and create some auto report functions
        *  extra extra allow managers to delete and edit review (like 1984)
        *  move default methods to ui helper class and refactor login */
    }
}
