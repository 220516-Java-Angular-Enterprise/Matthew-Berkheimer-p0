package com.revature.pens.ui;

import com.revature.pens.models.*;
import com.revature.pens.services.InventoryService;
import com.revature.pens.services.OrderService;
import com.revature.pens.services.PenService;
import com.revature.pens.services.StoreService;
import com.revature.pens.util.annotations.Inject;
import com.revature.pens.util.custom_scanner.QuitScanner;
import com.revature.pens.util.helper.UIHelper;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class StoreMenu implements IMenu{
    @Inject
    private final User user;
    @Inject
    private final StoreService storeService;
    @Inject
    private final UIHelper uiHelper;
    @Inject
    private final PenService penService;
    @Inject
    private final OrderService orderService;
    @Inject
    private final InventoryService inventoryService;

    public StoreMenu(User user, StoreService storeService, UIHelper uiHelper, PenService penService, OrderService orderService, InventoryService inventoryService){
        this.user = user;
        this.storeService = storeService;
        this.uiHelper = uiHelper;
        this.penService = penService;
        this.orderService = orderService;
        this.inventoryService = inventoryService;
    }

    @Override
    public void start() {
        if (user.getRole().equals("ADMIN")){
            adminView();
        }else {
            customerView();
        }
    }

    //todo add store orderhistory to the list
    //todo add checks in store service
    private void adminView(){
        List<String> choices = Arrays.asList("Add Store", "Edit Store", "Delete Store", "Add Inventory", "Edit Inventory", "Delete Inventory","Shop");
        QuitScanner quitScanner = QuitScanner.getInstance();
        exit:
        while (true) {
            uiHelper.comLineList(choices);
            switch (quitScanner.nextLine()) {
                case "1":
                    addStore();
                    break;
                case "2":
                    editStore();
                    break;
                case "3":
                    deleteStore();
                    break;
                case "4":
                    addInventory();
                    break;
                case "5":
                    editInventory();
                    break;
                case "6":
                    deleteInventory();
                    break;
                case "7":
                    customerView();
                    break;
                case "x":
                    break exit;
                default:
                    System.out.println("Invalid Input");
                    break;
            }
        }
    }

    private void addStore(){
        Store newStore = new Store();
        newStore.setId(UUID.randomUUID().toString());
        QuitScanner quitScanner = QuitScanner.getInstance();
        System.out.println("\nAdding new store...");
        while (true) {
            System.out.println("\nPlease enter store address");
            newStore.setAddress(quitScanner.nextLine());
            break;
        }

        while (true) {
            System.out.println("\nPlease enter city");
            String city = quitScanner.nextLine();
            if(storeService.isValidCity(city)) {
                newStore.setCity(city);
                break;
            }
            System.out.println("Invalid Input");
        }

        while (true) {
            System.out.println("\nPlease enter state");
            String state = quitScanner.nextLine();
            if(storeService.isValidState(state)) {
                newStore.setState(state);
                break;
            }
            System.out.println("Invalid Input");
        }

        System.out.println("\nConfirm (y/n)");
        System.out.println("\nStore address: " + newStore.getAddress());
        System.out.println("Store city: " + newStore.getCity());
        System.out.println("Store state: " + newStore.getState());
        switch (quitScanner.nextLine().toLowerCase()){
            case "y":
                System.out.println("Saving store...");
                storeService.register(newStore);
                break;
            case "n":
                break;
        }
    }

    private void editStore(){
        QuitScanner quitScanner = QuitScanner.getInstance();
        exit:
        while (true) {
            System.out.println("\nSelect from list of stores to delete");
            List<Store> storeList = storeService.getAllStores();
            List<String> choices = storeList.stream().map(store -> store.toString()).collect(Collectors.toList());
            uiHelper.comLineList(choices);
            String in = quitScanner.nextLine();
            if (in.matches("\\d+")) {
                int i = Integer.valueOf(in) - 1;
                if(i >= 0 && i < storeList.size()){
                    editStoreView(storeList.get(i));
                    break exit;
                }
            } else if (in.equals("x")) {
                break exit;
            }
            System.out.println("Invalid Input");
        }
    }

    private void editStoreView(Store store){
        QuitScanner quitScanner = QuitScanner.getInstance();

            System.out.println("\nEditing Store...");
            while (true) {
                System.out.println("\nPlease enter store address");
                store.setAddress(quitScanner.nextLine());
                break;
            }

            while (true) {
                System.out.println("\nPlease enter city");
                String city = quitScanner.nextLine();
                if(storeService.isValidCity(city)) {
                    store.setCity(city);
                    break;
                }
                System.out.println("Invalid Input");
            }

            while (true) {
                System.out.println("\nPlease enter state");
                String state = quitScanner.nextLine();
                if(storeService.isValidState(state)) {
                    store.setState(state);
                    break;
                }
                System.out.println("Invalid Input");
            }

            System.out.println("\nConfirm (y/n)");
            System.out.println("\nStore address: " + store.getAddress());
            System.out.println("Store city: " + store.getCity());
            System.out.println("Store state: " + store.getState());
            switch (quitScanner.nextLine().toLowerCase()){
                case "y":
                    System.out.println("Saving store changes...");
                    storeService.editStore(store);
                    break;
                case "n":
                    break;
            }
    }

    private void deleteStore(){
        QuitScanner quitScanner = QuitScanner.getInstance();
        exit:
        while (true) {
            System.out.println("\nSelect from list of stores to delete");
            List<Store> storeList = storeService.getAllStores();
            List<String> choices = storeList.stream().map(store -> store.toString()).collect(Collectors.toList());
            uiHelper.comLineList(choices);
            String in = quitScanner.nextLine();
            if (in.matches("\\d+")) {
                int i = Integer.valueOf(in) - 1;
                if(i >= 0 && i < storeList.size()){
                    storeService.deleteStore(storeList.get(i).getId());
                    break exit;
                }
            } else if (in.equals("x")) {
                break exit;
            }
            System.out.println("Invalid Input");
        }
    }

    //todo needs checks for valid store and pen id otherwise sql statement wont work
    private void addInventory(){
        Inventory inventory = new Inventory();
        QuitScanner quitScanner = QuitScanner.getInstance();
        System.out.println("\nAdding new inventory...");
        System.out.println("\nPlease enter store id");
        inventory.setStoreID(quitScanner.nextLine());

        System.out.println("\nPlease enter pen id");
        inventory.setPenID(quitScanner.nextLine());

        System.out.println("\nPlease enter amount");
        inventory.setAmount(Integer.valueOf(quitScanner.nextLine()));

        System.out.println("\nConfirm (y/n)");
        System.out.println("\nStore ID: " + inventory.getStoreID());
        System.out.println("Pen ID: " + inventory.getPen());
        System.out.println("Amount: " + inventory.getAmount());
        switch (quitScanner.nextLine().toLowerCase()){
            case "y":
                inventoryService.register(inventory);
                break;
            case "n":
                break;
        }
    }
    private void editInventory(){
        QuitScanner quitScanner = QuitScanner.getInstance();
        exit:
        while (true) {
            System.out.println("\nSelect from list of inventories");
            List<Inventory> inventoryList = inventoryService.getAllInventories();
            List<String> choices = inventoryList.stream().map(inventory -> inventory.toString()).collect(Collectors.toList());
            uiHelper.comLineList(choices);
            String in = quitScanner.nextLine();
            if (in.matches("\\d+")) {
                int i = Integer.valueOf(in) - 1;
                if(i >= 0 && i < inventoryList.size()){
                    Inventory inventory = inventoryList.get(i);
                    editInventoryView(inventory);
                    break exit;
                }
            } else if (in.equals("x")) {
                break exit;
            }
            System.out.println("Invalid Input");
        }
    }

    private void editInventoryView(Inventory inventory){
        QuitScanner quitScanner = QuitScanner.getInstance();
        System.out.println("\nEditing inventory...");

        System.out.println("\nPlease enter new amount");
        String in = quitScanner.nextLine();
        if(in.matches("\\d+")) {
            inventory.setAmount(Integer.valueOf(quitScanner.nextLine()));
        }
        System.out.println("\nConfirm (y/n)");
        System.out.println("\nStore ID: " + inventory.getStoreID());
        System.out.println("Pen ID: " + inventory.getPen());
        System.out.println("Amount: " + inventory.getAmount());
        switch (quitScanner.nextLine().toLowerCase()){
            case "y":
                System.out.println("Changing Inventory...");
                inventoryService.register(inventory);
                break;
            case "n":
                break;
        }
    }

    private void deleteInventory(){
        QuitScanner quitScanner = QuitScanner.getInstance();
        exit:
        while (true) {
            System.out.println("\nSelect from list of inventories");
            List<Inventory> inventoryList = inventoryService.getAllInventories();
            List<String> choices = inventoryList.stream().map(inventory -> inventory.toString()).collect(Collectors.toList());
            uiHelper.comLineList(choices);
            String in = quitScanner.nextLine();
            if (in.matches("\\d+")) {
                int i = Integer.valueOf(in) - 1;
                if(i >= 0 && i < inventoryList.size()){
                    Inventory inventory = inventoryList.get(i);
                    inventoryService.delete(inventory.getStoreID(),inventory.getPenID());
                    break exit;
                }
            } else if (in.equals("x")) {
                break exit;
            }
            System.out.println("Invalid Input");
        }
    }


    //todo allow shopper to leave customer view
    private void customerView(){
        uiHelper.header("Welcome To The Pens Store");
        System.out.println();
        //List<Pen> penList = penService.getPens();
        List<Inventory> inventoryList = inventoryService.getAllInventories();
        uiHelper.shopDisplay(inventoryList);
        System.out.println();
        System.out.println("\nInsert item number to buy");
        QuitScanner quitScanner = QuitScanner.getInstance();
        while (true){
            String s  = quitScanner.nextLine();
            if(s.matches("\\d+")) {
                int j = Integer.valueOf(s);
                for (int i = 1; i <= inventoryList.size(); i++) {
                    if (i == j) {
                        addOrder(inventoryList.get(i - 1));
                    }
                }
                break;
            }else {
                System.out.println("Invalid Input");
            }
        }
    }

    //todo add verification for amount
    public void addOrder(Inventory inventory){
        QuitScanner quitScanner = QuitScanner.getInstance();
        System.out.println("How many?");
        //todo take in input here
        int amount = Integer.valueOf(quitScanner.nextLine());

        System.out.println("Confirm order (y/n)");
            switch (quitScanner.nextLine().toLowerCase()) {
                case "y":
                    System.out.println("Placing Order...");
                    //todo add order here
                    Order order = new Order(UUID.randomUUID().toString(), inventory.getStoreID(), user.getId(), inventory.getPenID(), "INCART", LocalDateTime.now(),LocalDateTime.now(),amount);
                    orderService.register(order);
                    inventory.setAmount(inventory.getAmount() - amount);
                    inventoryService.update(inventory);
                    break;
                case "n":
                    System.out.println("Canceling Order...");
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }

    }



    private void addPen(){
        Scanner scan = new Scanner(System.in);
        String name;
        String type;
        String tipSize;
        String color;
        String description;
        boolean retractable;
        boolean refillable;
        double cost;

        System.out.print("\nPlease enter pen name: ");
        name = scan.nextLine();
        System.out.print("\nEnter pen type: ");
        type = scan.nextLine();
        System.out.print("\nEnter pen tip size: ");
        tipSize = scan.nextLine();
        System.out.print("\nEnter pen color: ");
        color = scan.nextLine();
        System.out.print("\nEnter pen description: ");
        description = scan.nextLine();

        retract:
        while (true) {
            System.out.print("\nIs pen retractable (y/n)");
            switch (scan.nextLine().toLowerCase(Locale.ROOT)) {
                case "y":
                    retractable = true;
                    break retract;
                case "n":
                    retractable = false;
                    break retract;
                default:
                    System.out.println("\nInvalid Input");
                    break;
            }
        }

        refill:
        while (true) {
            System.out.print("\nIs pen refillable (y/n)");
            switch (scan.nextLine().toLowerCase()) {
                case "y":
                    refillable = true;
                    break refill;
                case "n":
                    refillable = false;
                    break refill;
                default:
                    System.out.println("\nInvalid Input");
                    break;
            }
        }




    }
}
