package com.example.splash_ui.PetSection;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.splash_ui.Adapters.SoldPetAdminAdapter;
import com.example.splash_ui.Adapters.availablePetAdminAdapter;
import com.example.splash_ui.Admin.AddPet;
import com.example.splash_ui.Admin.AvailablePet;
import com.example.splash_ui.ModelClass.Upload;
import com.example.splash_ui.ModelClass.available_pet_Admin;
import com.example.splash_ui.PetAdapter.ShopAdadpter.PetFeatured;
import com.example.splash_ui.PetAdapter.ShopAdadpter.ShopAdapterHelperClass;
import com.example.splash_ui.R;
import com.example.splash_ui.PetAdapter.ShopAdadpter.RecyclerViewInterface;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class petShop extends AppCompatActivity {


    private RecyclerView recyclerView;
    private PetFeatured adapter;
    private ImageView shop;

    String username;


    private FirebaseDatabase root;
    private DatabaseReference ref;
    private List<Upload>model;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_shop);

       shop=findViewById(R.id.shopped);
        username=getIntent().getStringExtra("user");

        recyclerView=findViewById(R.id.rec1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




        model= new ArrayList<>();

        root=FirebaseDatabase.getInstance();
        ref=root.getReference("admin/uploads");

        adapter=new PetFeatured(model, petShop.this,username);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot Snapshot) {
                model.clear();
                for (DataSnapshot snapshot : Snapshot.getChildren()) {
                    String namedb = snapshot.child("name").getValue(String.class);
                 String descriptiondb = snapshot.child("description").getValue(String.class);
                    String imageurldb = snapshot.child("imageurl").getValue(String.class);
//
                    double pricedb;
                    if (snapshot.child("price").exists()) {
                        pricedb = snapshot.child("price").getValue(double.class);
                    } else {
                        pricedb = 0.0;
                    }


                    model.add(new Upload(namedb,descriptiondb, pricedb, imageurldb));
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recyclerView.setAdapter(adapter);


        //////////////////////////////////////////////////////////////////////////////

      shop.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent=new Intent(petShop.this, purchased_pets.class);
              intent.putExtra("user",username);
              startActivity(intent);

          }
      });


    }
}

