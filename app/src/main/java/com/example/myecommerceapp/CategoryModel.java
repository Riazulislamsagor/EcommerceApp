package com.example.myecommerceapp;

public class CategoryModel {

    String imgurl;
    String name;
    String type;

    public CategoryModel() {
    }

    public CategoryModel(String imgurl, String name, String type) {
        this.imgurl = imgurl;
        this.name = name;
        this.type = type;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
