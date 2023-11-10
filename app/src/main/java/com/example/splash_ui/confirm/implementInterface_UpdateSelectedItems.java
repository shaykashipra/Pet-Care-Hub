package com.example.splash_ui.confirm;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;

public class implementInterface_UpdateSelectedItems extends Application implements UpdateSelectedItems{

    private static Context context; //variable of context
    ArrayList<OrderListModel> orderListModels; //array list of model class


    //override model class


    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();//initialize context
        orderListModels=new ArrayList<>(); //initialize arraylist
    }

    //create a method with context return type
    public static Context getCurrentContext(){

        return context;

    }

    @Override
    public void additems(int image,String name, double price) {
       orderListModels.add(new OrderListModel(image,name,price));


    }



    @Override
    public ArrayList<OrderListModel> getItems() {
        return orderListModels;
    }
}
