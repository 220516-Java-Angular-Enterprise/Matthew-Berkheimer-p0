package com.revature.pens.ui;

import java.util.Scanner;
import java.util.UUID;

import com.revature.pens.models.User;
import com.revature.pens.services.UserService;
import com.revature.pens.util.annotations.Inject;

public class LoginMenu implements IMenu{
    @Inject
    private final UserService userService;

    @Inject
    public LoginMenu(UserService userService){
        this.userService = userService;
    }

    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);
        exit:
        {
            while (true) {
                startUpMessage();
                System.out.println("\n Enter: ");
                String in = scan.nextLine();
                switch (in) {
                    case "1":
                        login();
                        break;
                    case "2":
                        signUp();
                        //todo exit exit loop?
                        break;
                    case "x":
                        System.out.println("\n Ending...");
                        break exit;
                    default:
                        System.out.println("\n Invalid Input");
                        break;

                }
            }
        }



    }

    private void startUpMessage(){
        System.out.println("\n Welcome to Money Drain");
        System.out.println("[1] Login");
        System.out.println("[2] Signup");
        System.out.println("[x] Exit");
    }

    private void login(){
        //todo this method
        String username;
        String password;
        Scanner scan = new Scanner(System.in);

        while(true) {
            System.out.println("\nEnter Username: ");
            username = scan.nextLine();
            System.out.println("\nEnter Password: ");
            password = scan.nextLine();
        }



    }

    private void signUp(){
        //todo check for unique usernames
        String username;
        String password;
        Scanner scan = new Scanner(System.in);

        fullLoop:
        while(true) {
            while (true) {
                System.out.println("\nPlease Enter Username");
                username = scan.nextLine();
                if (!userService.isValidUsername(username)) {
                    System.out.println("\nInvalid Username. Username must be between 8-20 characters");
                } else {
                    break;
                }
            }

            passLoop:
            while (true) {
                System.out.println("\nPlease Enter Password");
                password = scan.nextLine();
                if (!userService.isValidPassword(password)) {
                    System.out.println("Invalid Password. Minimum eight characters, at least one letter, one number and one special character.");
                    continue;
                } else {
                    System.out.println("\nRe enter Password");
                    String pass2 = scan.nextLine();
                    if (!pass2.equals(password)) {
                        System.out.println("\nError Incorrect Password");
                        continue;
                    }
                }
                while (true) {
                    System.out.println("\nConfirm Credentials (y/n)");
                    System.out.println("Username: " + username);
                    System.out.println("Password: " + password);
                    //TODO ASK FOR EMAIL
                    System.out.println("\nEnter :");
                    String confirm = scan.nextLine().toLowerCase();
                    switch (confirm) {
                        case "y":
                            User user = new User(UUID.randomUUID().toString(),username,password,"DEFAULT", "PUT EMAIL HERE");
                            new StoreMenu(user).start();
                            break fullLoop;
                        case "n":
                            continue fullLoop;
                        default:
                            System.out.println("Invalid Character");
                            continue;
                    }
                }
            }
        }

    }
}
