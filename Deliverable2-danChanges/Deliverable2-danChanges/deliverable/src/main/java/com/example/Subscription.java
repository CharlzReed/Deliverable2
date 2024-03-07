package com.example;

public class Subscription {

    public int subscriptionID;
    public int userID;
    public String newsLetter;

    private Subscription(User user, String newsLetter){
        this.userID = user.getUserID();
        this.newsLetter = newsLetter;
    }


    public void subscribe(User user, String n) {
        Subscription s = new Subscription(user, n);
        if(user.findSub(s)){
            user.addSubscription(s);
        }
    }


    public void unsubscribe(User user, Subscription s) {
        if(user.findSub(s)){
            for(Subscription sub : user.getSubscriptions){
                if(sub.equals(s)){
                    user.removeSubscription(sub);
                    break;
                }
            }
        }
    }

}
