package com.example;

public class OverdueEvent {
    private final Item item;
    private final User user;

    public OverdueEvent(Item item, User user) {
        this.item = item;
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public User getUser() {
        return user;
    }
}