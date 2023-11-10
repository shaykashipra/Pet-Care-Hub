package com.example.splash_ui.confirm;

import androidx.recyclerview.widget.RecyclerView;

public class OrderListModel {

    int image;
    String name,price_val;
    double price;
    public OrderListModel(int image, String name, double price){
        this.image = image;
        this.name = name;

        this.price = price;

    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }



    public double getPrice() {
        return price;
    }
}
