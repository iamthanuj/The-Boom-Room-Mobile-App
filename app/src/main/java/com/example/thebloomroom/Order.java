package com.example.thebloomroom;

public class Order {
    private String itemName;
    private String itemPrice;
    private String orderLocation;

    private String orderCusName;

    // Constructors, getters, setters, etc.

    // Constructor
    public Order(String itemName, String itemPrice, String orderLocation, String orderCusName) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.orderLocation = orderLocation;
        this.orderCusName = orderCusName;
    }

    // Getters and setters...

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getOrderLocation() {
        return orderLocation;
    }

    public void setOrderLocation(String orderLocation) {
        this.orderLocation = orderLocation;
    }

    public String getOrderCusName() {
        return orderCusName;
    }


}

