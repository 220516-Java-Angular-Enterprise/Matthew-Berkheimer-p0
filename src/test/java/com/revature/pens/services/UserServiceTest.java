package com.revature.pens.services;

import com.revature.pens.daos.UserDAO;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {
    UserService userService = new UserService(new UserDAO());

    @Test
    public void register() {
    }

    @Test
    public void checkUser() {
    }

    @Test
    public void isValidUsername() {
    }

    @Test
    public void isValidPassword() {
    }

    @Test
    public void isValidEmail() {
        //Act
        //String email = "job@gmail.com";
        //"job.test@gmail.com" valid
        //"job.test@gmail-.cc" invalid
        //"job.test@-gmail.cc" invalid
        //"job.test@gm-ail.cc" valid
        //"job..test@gmail.cc" invalid
        //"jo-b.test@gmail.cc" valid
        //"jo--b.test@gmail.cc" invalid
        //"jobtest.@gmail.cc" invalid
        //"-jobtest@gmail.cc" invalid
        String email = "-jobtest@gmail.cc";
        //Arrange
        boolean isTrue = userService.isValidEmail(email);
        //Assert
        assertTrue(isTrue);
    }
}