package com.revature.pens.ui;

import com.revature.pens.models.Order;
import com.revature.pens.models.User;
import com.revature.pens.services.OrderService;
import com.revature.pens.util.annotations.Inject;
import com.revature.pens.util.custom_scanner.QuitScanner;
import com.revature.pens.util.helper.UIHelper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CartMenu implements IMenu{
    @Inject
    private final OrderService orderService;
    @Inject
    private final UIHelper uiHelper;
    @Inject
    private final User user;

    public CartMenu(User user,OrderService orderService, UIHelper uiHelper){
        this.user = user;
        this.orderService = orderService;
        this.uiHelper = uiHelper;
    }

    @Override
    public void start() {
        QuitScanner quitScanner = QuitScanner.getInstance();
        //uiHelper.orderDisplay();
        List<Order> allOrders = orderService.getCustomerOrders(user.getId());
        uiHelper.header("Shipped");
        List<Order> ordered = allOrders.stream().filter(order -> order.getStatus().equals("SHIPPING")).collect(Collectors.toList());
        uiHelper.orderDisplayShipped(ordered);
        System.out.println();
        uiHelper.header(user.getUsername() + "'s Cart");
        List<Order> inCart = allOrders.stream().filter(order -> order.getStatus().equals("INCART")).collect(Collectors.toList());
        uiHelper.orderDisplay(inCart);
        System.out.println();
        exit:
        while (true){
            System.out.println("Purchase Order y/n");
            switch (quitScanner.nextLine().toLowerCase()){
                case "y":
                    for (Order order:inCart) {
                        order.setStatus("SHIPPING");
                        order.setOrderSent(LocalDateTime.now());
                        orderService.update(order);
                    }
                    break exit;
                case "n":
                    break exit;
                default:
                    System.out.println("Invalid Input");
                    break;
            }
        }
    }
}
