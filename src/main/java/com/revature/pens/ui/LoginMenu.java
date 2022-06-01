package com.revature.pens.ui;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import com.revature.pens.models.User;
import com.revature.pens.services.UserService;
import com.revature.pens.util.annotations.Inject;
import com.revature.pens.util.custom_scanner.QuitScanner;
import com.revature.pens.util.helper.UIHelper;

public class LoginMenu implements IMenu{
    @Inject
    private final UserService userService;

    @Inject
    public LoginMenu(UserService userService){
        this.userService = userService;
    }

    @Override
    public void start() {
        //Scanner scan = new Scanner(System.in);
        QuitScanner scan = QuitScanner.getInstance();
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
        //System.out.println("\n Welcome to Pens");
        header("Welcome to Pens");
        System.out.println("[1] Login");
        System.out.println("[2] Signup");
        System.out.println("[x] Exit");
    }


    private void login(){
        //todo add a print out if user fails to login
        String username;
        String password;
        //Scanner scan = new Scanner(System.in);
        QuitScanner scan = QuitScanner.getInstance();

        while(true) {
            System.out.println("\nEnter Username: ");
            username = scan.nextLine();
            System.out.println("\nEnter Password: ");
            password = scan.nextLine();
            break;
        }

        List<User> eUsers = userService.checkUser();
        for (User check: eUsers) {
            if (check.getUsername().equals(username) && check.getPassword().equals(password)){
                new MainMenu(check, new UIHelper()).start();
            }
        }
    }

    private void signUp(){
        //todo check for unique usernames and make sure that the signup redirects to the main menu
        String username;
        String password;
        String email;
        String name;
        String address;
        String phone;
        String creditCard;
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
                    System.out.println("\nPlease Enter Name");
                    name = scan.nextLine();
                        break;
                }
                while (true) {
                    System.out.println("\nPlease Enter Email");
                    email = scan.nextLine();
                    if (!userService.isValidEmail(email)) {
                        System.out.println("\nInvalid Email");
                    } else {
                        break;
                    }
                }
                while (true) {
                    System.out.println("\nPlease Enter Address");
                    address = scan.nextLine();
                    break;
                }
                while (true) {
                    System.out.println("\nPlease Enter Phone");
                    phone = scan.nextLine();
                    break;
                }
                while (true) {
                    System.out.println("\nPlease Enter Credit card");
                    creditCard = scan.nextLine();
                    break;
                }


                while (true) {
                    System.out.println("\nConfirm Credentials (y/n)");
                    System.out.println("Username: " + username);
                    System.out.println("Password: " + password);
                    System.out.println("Name: " + name);
                    System.out.println("Email: " + email);
                    System.out.println("Address: " + address);
                    System.out.println("Phone: " + phone);
                    System.out.println("Credit Card: " + creditCard);
                    //TODO ASK FOR EMAIL
                    System.out.println("\nEnter :");
                    String confirm = scan.nextLine().toLowerCase();
                    switch (confirm) {
                        case "y":
                            User user = new User(UUID.randomUUID().toString(),username,password,"DEFAULT", email, name, address, phone, creditCard);
                            userService.register(user);
                            new MainMenu(user, new UIHelper());
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
