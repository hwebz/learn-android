package com.hado.ecommercehealthyapp.Domains;

import java.io.Serializable;

public class BestDeal implements Serializable {
    private String title;
    private String imgPath;
    private double price;
    private String description;
    private double rate;

    public BestDeal(String title, String imgPath, double price, String description, double rate) {
        this.title = title;
        this.imgPath = imgPath;
        this.price = price;
        this.description = description;
        this.rate = rate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
