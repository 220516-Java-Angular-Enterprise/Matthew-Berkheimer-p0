package com.revature.pens.ui;

import com.revature.pens.models.User;
import com.revature.pens.services.UserService;
import com.revature.pens.util.annotations.Inject;
import com.revature.pens.util.custom_exceptions.InvalidUserDeletionException;
import com.revature.pens.util.custom_scanner.QuitScanner;
import com.revature.pens.util.helper.UIHelper;

import java.util.List;
import java.util.stream.Collectors;

public class UserManMenu implements IMenu{
    @Inject
    private final UserService userService;
    @Inject
    private final UIHelper uiHelper;
    @Inject
    private final User user;

    public UserManMenu(UserService userService, UIHelper uiHelper, User user){
        this.userService = userService;
        this.uiHelper = uiHelper;
        this.user = user;
    }

    @Override
    public void start() {
        uiHelper.header("Select User to Delete");
        QuitScanner quitScanner = QuitScanner.getInstance();
        try {
            exit:
            while (true) {
                System.out.println("\nSelect from list of stores to delete");
                List<User> userList = userService.getAllUser();
                List<String> choices = userList.stream().map(user -> user.toString()).collect(Collectors.toList());
                uiHelper.comLineList(choices);
                String in = quitScanner.nextLine();
                if (in.matches("\\d+")) {
                    int i = Integer.valueOf(in) - 1;
                    if (i >= 0 && i < userList.size()) {
                        if (!userList.get(i).getId().equals(user.getId())) {
                            userService.removeUser(userList.get(i));
                            break exit;
                        } else {
                            throw new InvalidUserDeletionException("Cannot Delete Current Active User");
                        }
                    }
                } else if (in.equals("x")) {
                    break exit;
                }
                System.out.println("Invalid Input");
            }
        }catch (InvalidUserDeletionException e){
            System.out.println(e.getMessage());
        }
    }
}
