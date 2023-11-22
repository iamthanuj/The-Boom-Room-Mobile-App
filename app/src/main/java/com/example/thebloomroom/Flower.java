package com.example.thebloomroom;

public class Flower {
    private int flowerId;
    private String flowerName;
    private String flowerColor;
    private String description;
    private String price;

    // Constructor
    public Flower(int flowerId, String flowerName, String flowerColor, String description, String price) {
        this.flowerId = flowerId;
        this.flowerName = flowerName;
        this.flowerColor = flowerColor;
        this.description = description;
        this.price = price;
    }

    // Getters and setters
    public int getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(int flowerId) {
        this.flowerId = flowerId;
    }

    public String getFlowerName() {
        return flowerName;
    }

    public void setFlowerName(String flowerName) {
        this.flowerName = flowerName;
    }

    public String getFlowerColor() {
        return flowerColor;
    }

    public void setFlowerColor(String flowerColor) {
        this.flowerColor = flowerColor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

