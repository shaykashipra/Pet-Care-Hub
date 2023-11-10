package com.example.splash_ui.PetAdapter.ShopAdadpter;

public class DynamicRVModel {
    String name,weight;
    double price;

    int image;
    public DynamicRVModel(int image,String name,String weight,double price) {
        this.image=image;
       this.name = name;
        this.price=price;
        this.weight=weight;
    }

    public String getName(){
        return name;
    }

    public int getImage() {
        return image;
    }

    public String getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }
}
