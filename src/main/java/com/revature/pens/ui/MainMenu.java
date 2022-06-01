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
    @Override
    public void start() {
        if(user.getRole().equals("ADMIN")){
            adminView();
        } else{
            userView();
        }
    }
    public void adminView(){
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
    }

    public void userView(){
        exit:
        while(true) {
            System.out.println();
            QuitScanner quitScanner = QuitScanner.getInstance();
            menu = Arrays.stream(new String[]{"Edit Profile", "Cart", "Shop"}).collect(Collectors.toList());
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
                case "x":
                    break exit;
                default:
                    System.out.println("\nInvalid Input");
                    break;
            }
        }
    }
}
