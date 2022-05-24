package com.revature.pens.ui;

import com.revature.pens.models.User;

import java.util.Locale;
import java.util.Scanner;

public class StoreMenu implements IMenu{
    private final User user;

    public StoreMenu(User user){
        this.user = user;
    }

    @Override
    public void start() {
        System.out.println("\n\nInsert shop menu here");
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
            System.out.print("\nIs pen retractable (y/n)");
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
