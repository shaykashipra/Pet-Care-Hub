package com.example.splash_ui.PetSection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.splash_ui.R;
import com.squareup.picasso.Picasso;

public class PetItemsDetails extends AppCompatActivity {

    ImageView pet_img;
    TextView pet_title,pet_description,pet_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_items_details);

        Intent intent=getIntent();

        String  image=intent.getStringExtra("url");
        String name=intent.getStringExtra("name");
        String description=intent.getStringExtra("description");
        Double price=intent.getDoubleExtra("price",0.0);


        //hook

        pet_img=findViewById(R.id.pet_img);
        pet_title=findViewById(R.id.pet_title);
        pet_description=findViewById(R.id.pet_description);
        pet_price=findViewById(R.id.pet_price);



        Picasso.get().load(image).into(pet_img);
        pet_title.setText(name);
        pet_description.setText(description);
        pet_price.setText("BDT  "+String.valueOf(price));




    }
}