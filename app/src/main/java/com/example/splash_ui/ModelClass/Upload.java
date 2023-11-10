package com.example.splash_ui.ModelClass;

public class Upload{
    private String name,imageurl,description;

    double price;

    public Upload(){

    }

    public Upload(String name,String description,double price, String imageurl) {

        if(name.trim().equals("")){
            name="no name";
        }
        this.name = name;
        this.imageurl = imageurl;
        this.description=description;
        this.price=price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getImageurl() {
        return imageurl;
    }
}