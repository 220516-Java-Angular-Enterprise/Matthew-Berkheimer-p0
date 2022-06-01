package com.revature.pens.ui;

import com.revature.pens.models.User;
import com.revature.pens.services.CustomerService;
import com.revature.pens.services.UserService;
import com.revature.pens.util.annotations.Inject;
import com.revature.pens.util.custom_scanner.QuitScanner;
import com.revature.pens.util.helper.UIHelper;

import java.util.Arrays;
import java.util.List;

public class EditProfileMenu implements IMenu {
    @Inject
    private final User user;
    @Inject
    private final UserService userService;
    @Inject
    private final UIHelper uiHelper;

    public EditProfileMenu(User user, UserService userService, UIHelper uiHelper){
        this.user = user;
        this.userService = userService;
        this.uiHelper = uiHelper;
    }


    @Override
    public void start() {
        String username;
        String password;
        String email;
        String name;
        String address;
        String phone;
        String cCard;

        QuitScanner quitScanner = QuitScanner.getInstance();

        exit:
        while(true) {
            List<String> userInfo = Arrays.asList("Username:\t" + user.getUsername(),
                    "Password:\t" + user.getPassword(),
                    "Email:\t\t" + user.getEmail(),
                    "Name:\t\t" + user.getName(),
                    "Address:\t" + user.getAddress(),
                    "Phone:\t\t" + user.getPhone(),
                    "C-Card:\t\t" + user.getCcToken());

            uiHelper.header("Edit " + user.getUsername() + "'s profile here");
            System.out.println("\nSelect Attribute to Edit");
            uiHelper.comLineList(userInfo);
                switch (quitScanner.nextLine()) {
                    case "1":
                        while (true) {
                            System.out.println("\nEnter new username");
                            username = (quitScanner.nextLine());
                            if (userService.isValidUsername(username)) {
                                user.setUsername(username);
                                break;
                            } else {
                                System.out.println("Invalid Username");
                            }
                        }
                        break ;
                    case "2":
                        while (true) {
                            System.out.println("\nEnter new password");
                            password = quitScanner.nextLine();
                            if (userService.isValidPassword(password)) {
                                user.setPassword(password);
                                break;
                            } else {
                                System.out.println("Invalid Password");
                            }
                        }
                        break ;
                    case "3":
                        while (true) {
                            System.out.println("\nEnter new email");
                            email = quitScanner.nextLine();
                            if (userService.isValidEmail(email)) {
                                user.setEmail(email);
                                break;
                            } else {
                                System.out.println("Invalid Email");
                            }
                        }
                        break ;
                    case "4":
                        System.out.println("\nEnter new name");
                        user.setName(quitScanner.nextLine());
                        break ;
                    case "5":
                        System.out.println("\nEnter new address");
                        user.setAddress(quitScanner.nextLine());
                        break ;
                    case "6":
                        while (true) {
                            System.out.println("\nEnter new phone");
                            phone = quitScanner.nextLine();
                            if (userService.isValidPhone(phone)) {
                                user.setPhone(phone);
                                break;
                            } else {
                                System.out.println("Invalid phone");
                            }
                        }
                        break ;
                    case "7":
                        while (true) {
                            System.out.println("\nEnter new credit card");
                            cCard = quitScanner.nextLine();
                            if (userService.isValidPhone(cCard)) {
                                user.setCcToken(cCard);
                                break;
                            } else {
                                System.out.println("Invalid Credit Card");
                            }
                        }
                        break ;
                    case "x":
                        break exit;
                    default:
                        System.out.println("Invalid Input");
                        continue exit;
                }
            userService.edit(user);
            System.out.println(user.getId());
        }
    }
}
