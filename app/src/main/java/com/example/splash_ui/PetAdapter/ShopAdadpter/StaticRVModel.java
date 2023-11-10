package com.example.splash_ui.PetAdapter.ShopAdadpter;

import android.app.Activity;

public class StaticRVModel {
    private int image;
    private String text;


    public StaticRVModel(int image, String text) {
        this.image = image;
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public String getText() {
        return text;
    }
}
