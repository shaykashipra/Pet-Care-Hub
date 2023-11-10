package com.example.splash_ui.ModelClass;

public class available_pet_Admin {
    String Animal_name;
    double Animal_price;

    String ImageUrl;

    public available_pet_Admin(String animal_name, double animal_price,String imageurl) {
        Animal_name = animal_name;
        Animal_price = animal_price;
        ImageUrl=imageurl;
    }

    public String getAnimal_name() {
        return Animal_name;
    }

    public double getAnimal_price() {
        return Animal_price;
    }

    public String getImageUrl() {
        return ImageUrl;
    }
}
