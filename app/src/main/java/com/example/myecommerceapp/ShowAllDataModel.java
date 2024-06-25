package com.example.myecommerceapp;

import java.io.Serializable;

public class ShowAllDataModel implements Serializable {
    String description,name,rating;
    int price;
    String imgurl;
    String type;

    public ShowAllDataModel() {
    }

    public ShowAllDataModel(String description, String name, String rating, int price, String imgurl, String type) {
        this.description = description;
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.imgurl = imgurl;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
