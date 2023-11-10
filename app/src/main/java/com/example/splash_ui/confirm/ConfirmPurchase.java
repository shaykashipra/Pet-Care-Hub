package com.example.splash_ui.confirm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.splash_ui.GroceryShop;
import com.example.splash_ui.PetAdapter.ShopAdadpter.DynamicRVModel;
import com.example.splash_ui.R;

import java.util.ArrayList;

public class ConfirmPurchase extends AppCompatActivity {
     Toolbar toolbar;
     Activity Context;
     UpdateSelectedItems updateSelectedItems;
     OrderListModel orderListModel;
     ImageView back;
     TextView total_price,empty;
     double price;

     RecyclerView orderRv;

     ConfirmOrderActivityAdapter confirmOrderActivityAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_purchase);

        toolbar=findViewById(R.id.toolbar);

        orderRv=findViewById(R.id.rv1);

        total_price=findViewById(R.id.total_price);
        empty=findViewById(R.id.empty);


        back=findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ConfirmPurchase.this, GroceryShop.class));
            }
        });

         orderListModel=(OrderListModel)getIntent().getSerializableExtra("orderlist");

     //    total_price.setText(String.valueOf(orderListModel.getPrice()));

       // UpdateSelectedItems updateSelectedItems = (UpdateSelectedItems) getApplication();
        ArrayList<OrderListModel> selectedItems = updateSelectedItems.getItems();


        confirmOrderActivityAdapter=new ConfirmOrderActivityAdapter(this,selectedItems);
        orderRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        orderRv.setAdapter(confirmOrderActivityAdapter);



    }
}