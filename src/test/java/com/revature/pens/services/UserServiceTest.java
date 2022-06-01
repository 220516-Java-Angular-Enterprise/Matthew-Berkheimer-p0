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
    public void isValidPhone(){
        //Act
        String phone = "3334445555";
        //Arrange
        boolean isTrue = userService.isValidPhone(phone);
        //Assert
        assertTrue(isTrue);
    }

    @Test
    public void isValidCC(){
        //Act
        String cc = "4444555566667777";
        //Arrange
        boolean isTrue = userService.isValidCC(cc);
        //Assert
        assertTrue(isTrue);
    }

    @Test
    public void isValidUsername() {
        //Act
        String password = "Eightcha";
        //Arrange
        boolean isTrue = userService.isValidUsername(password);
        //Assert
        assertTrue(isTrue);
    }

    @Test
    public void isValidPassword() {
        //Act
        String password = "P@ssw0rd";
        //Arrange
        boolean isTrue = userService.isValidPassword(password);
        //Assert
        assertTrue(isTrue);
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
        //Arrange
        //Assert
        assertArrayEquals(new boolean[]{false, false, false, true, false, true, false, false, true}, new boolean[]{userService.isValidEmail("-jobtest@gmail.cc"),
                                                                                                                    userService.isValidEmail("jobtest.@gmail.cc"),
                                                                                                                    userService.isValidEmail("jo--b.test@gmail.cc"),
                                                                                                                    userService.isValidEmail("jo-b.test@gmail.cc"),
                                                                                                                    userService.isValidEmail("job..test@gmail.cc"),
                                                                                                                    userService.isValidEmail("job.test@gm-ail.cc"),
                                                                                                                    userService.isValidEmail("job.test@-gmail.cc"),
                                                                                                                    userService.isValidEmail("job.test@gmail-.cc"),
                                                                                                                    userService.isValidEmail("job.test@gmail.com"),});
    }
}