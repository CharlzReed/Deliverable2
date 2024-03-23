package com.example;

public class ItemContext {
    private ItemState state;
    private Item item; // The item associated with this context

    public ItemContext(Item item) {
        this.item = item;
        this.state = new AvailableState(); // default state
    }

    public void setState(ItemState state) {
        this.state = state;
    }

    public void checkout() {
        state.checkout(this);
    }

    public void returnItem() {
        state.returnItem(this);
    }

    public Item getItem() {
        return item;
    }

    public ItemState getState() {
        return state;
    }
}
