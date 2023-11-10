package com.example.splash_ui.PetSection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.example.splash_ui.Adapters.SoldPetAdminAdapter;
import com.example.splash_ui.ModelClass.available_pet_Admin;
import com.example.splash_ui.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class purchased_pets extends AppCompatActivity {
   private String username;
    private RecyclerView recyclerView2;
    private SoldPetAdminAdapter adapter2;


    private FirebaseDatabase root2;
    private DatabaseReference ref2;
    private List<available_pet_Admin> model2;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchased_pets);


        username=getIntent().getStringExtra("user");

        recyclerView2=findViewById(R.id.rec1);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));




        model2= new ArrayList<>();

        root2=FirebaseDatabase.getInstance();
        ref2=root2.getReference("users/"+username+"/cart");

        adapter2=new SoldPetAdminAdapter(model2, purchased_pets.this);




        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot Snapshot) {
                model2.clear();
                if (Snapshot.exists()) {
                    for (DataSnapshot snapshot : Snapshot.getChildren()) {
                        String namedb = snapshot.child("name").getValue(String.class);
//                   String descriptiondb = snapshot.child("description").getValue(String.class);
                        String imageurldb = snapshot.child("imageurl").getValue(String.class);
//
                        double pricedb;
                        if (snapshot.child("price").exists()) {
                            pricedb = snapshot.child("price").getValue(double.class);
                        } else {
                            pricedb = 0.0;
                        }


                        model2.add(new available_pet_Admin(namedb, pricedb, imageurldb));
                    }
                    adapter2.notifyDataSetChanged();

                }
                else{
                    Toast.makeText(purchased_pets.this, "No Item Purchased Yet", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recyclerView2.setAdapter(adapter2);



    }
}

