package com.revature.pens.services;

import com.revature.pens.daos.StoreDAO;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class StoreServiceTest {
    StoreService storeService = new StoreService(new StoreDAO());

    @Test
    public void register() {
    }

    @Test
    public void isValidAddress() {
    }

    @Test
    public void isValidCity() {
        //Act
        //" test" invalid
        //"te st" valid
        //"test " invalid
        //"te  st" invalid
        //"test" valid
        String city = "test";
        //Arrange
        boolean isTrue = storeService.isValidCity(city);
        //Assert
        assertArrayEquals(new boolean[]{false, true, false, false, true}, new boolean[]{storeService.isValidCity(" test"), storeService.isValidCity("te st"), storeService.isValidCity("test "),storeService.isValidCity("te  st"),storeService.isValidCity("test")});
    }

    @Test
    public void isValidState() {
        //Act
        //"WA" valid
        //"W A" invalid
        //" WA" invalid
        //"WA " invalid
        //"WZ" invalid
        //Arrange
        List<String> states = Arrays.asList("WZ", "WA ", " WA", "W A", "WA");
        //Assert
        //todo this line here
        assertArrayEquals(new boolean[]{false, false, false, false, true}, new boolean[]{storeService.isValidState("WZ"), storeService.isValidState("WA "), storeService.isValidState(" WA"),storeService.isValidState("W A"),storeService.isValidState("WA")});
    }
}