package com.example.ManualTests;

import org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.example.Course;
import com.example.Item;
import com.example.ItemType;
import com.example.Library;
import com.example.LocationType;
import com.example.OverdueEvent;
import com.example.StatusType;
import com.example.User;
import com.example.UserType;

public class OverdueEventTest {
    @Test
    public void testOverdueEventInitialization_and_GetMethods() {
        String name = "David Green";
        String email = "d.green@gmail.com";
        String password = "hello123";
        UserType userType = UserType.STUDENT;
        double accountBalance = 0.00;
        int userID = Library.generateNextUserId();

        User testUser = new User.UserBuilder(userID, name)
                .email(email)
                .password(password)
                .userType(userType)
                .accountBalance(accountBalance)
                .build();

        Item testItem = new Item(0, "Example Book", ItemType.BOOK, LocationType.NORTH_SHELF, 9.99, StatusType.ACTIVE);

        OverdueEvent testOverdueEvent = new OverdueEvent(testItem, testUser);
        Assert.assertEquals(testItem, testOverdueEvent.getItem());
        Assert.assertEquals(testUser, testOverdueEvent.getUser());
    }
}
