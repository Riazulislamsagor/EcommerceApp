package com.example.myecommerceapp;

public class AddressModel {
    String userAdrees;
    boolean isselected;

    public AddressModel() {
    }

    public AddressModel(String userAdrees, boolean isselected) {
        this.userAdrees = userAdrees;
        this.isselected = isselected;
    }

    public String getUserAdrees() {
        return userAdrees;
    }

    public void setUserAdrees(String userAdrees) {
        this.userAdrees = userAdrees;
    }

    public boolean isIsselected() {
        return isselected;
    }

    public void setIsselected(boolean isselected) {
        this.isselected = isselected;
    }
}
