package com.example;

public class Subscription extends Item {
    private String provider;
    private String subscriptionType;

    public Subscription(int itemID, String name, LocationType locationInLibrary, double cost, StatusType statusType,
            String provider, String subscriptionType) {
        super(itemID, name, ItemType.SUBSCRIPTION, locationInLibrary, cost, statusType);
        this.provider = provider;
        this.subscriptionType = subscriptionType;
    }

    public String getProvider() {
        return provider;
    }

    public String getSubType() {
        return subscriptionType;
    }
}
