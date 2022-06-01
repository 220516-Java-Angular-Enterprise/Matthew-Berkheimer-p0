package com.revature.pens.util.helper;

import com.revature.pens.models.Inventory;
import com.revature.pens.models.Order;
import com.revature.pens.models.Pen;
import com.revature.pens.models.Store;

import java.util.List;

public class UIHelper {

    public void header(String s){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= s.length() + 3; i++) {
            if (i == 0 || i == s.length() + 3) sb.append('+');
            else sb.append('-');
        }

        for (int j = 0; j < 3; j++) {
            if(j != 1) {
                System.out.print(sb);
            }else System.out.print("| " + s + " |");
            System.out.println();
        }
    }

    public void comLineList(List<String> choices){
        for (int i = 0; i < choices.size(); i++) {
            System.out.println("[" + (i+1) + "] " + choices.get(i));
        }
        System.out.println("[x] Back");
    }

    public void shopDisplay(List<Inventory> inventories){
        for(int i = 0; i < inventories.size(); i++){
            Pen pen = inventories.get(i).getPen();
            Store store = inventories.get(i).getStore();
            System.out.println("[" + (i+1) + "] " + pen.getName() + ", Amount: " + inventories.get(i).getAmount());
            System.out.println("\t|-" + pen.getColor() + ", " + pen.getTipSize() + ", " + pen.getCost());
            System.out.println("\t|-" + store.getAddress() + ", " + store.getCity() + ", " + store.getState());

        }

    }

    public void orderDisplay(List<Order> orderList){
        for (int i = 0; i < orderList.size(); i++){
            Order order = orderList.get(i);
            System.out.println("[" + (i+1) + "] " + order.getPenName() + ", " + order.getAmount() + ", " + order.getStatus() + ", " + order.getCustomerName());
            System.out.println("\t|-" + order.getStoreAddress());
        }
    }

    public void orderDisplayShipped(List<Order> orderList){
        for (int i = 0; i < orderList.size(); i++){
            Order order = orderList.get(i);
            System.out.println("[" + (i+1) + "] " + order.getPenName() + ", " + order.getAmount() + ", " + order.getStatus() + ", " + order.getCustomerName());
            System.out.println("\t|-" + order.getStoreAddress());
            System.out.println("\t|-" + order.getOrderSent());
        }
    }
    //todo create special Line reader for sorting through pens
}
