package com.example.splash_ui.Admin;

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
import com.example.splash_ui.ModelClass.Upload;
import com.example.splash_ui.ModelClass.available_pet_Admin;
import com.example.splash_ui.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AvailablePet extends AppCompatActivity {

    ImageView add ;
    private RecyclerView recyclerView,recyclerView2;
    private availablePetAdminAdapter adapter;
    private SoldPetAdminAdapter adapter2;

    private FirebaseDatabase root,root2;
    private DatabaseReference ref,ref2;
    private List<available_pet_Admin> model,model2;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_pet);

        add=findViewById(R.id.add_pet);

        recyclerView=findViewById(R.id.available_pet_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        recyclerView2=findViewById(R.id.sold_pet_rv);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        model= new ArrayList<>();

        root=FirebaseDatabase.getInstance();
        ref=root.getReference("admin/uploads");

        adapter=new availablePetAdminAdapter(model,AvailablePet.this);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot Snapshot) {
                   model.clear();
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


                        model.add(new available_pet_Admin(namedb, pricedb, imageurldb));
                    }
                    adapter.notifyDataSetChanged();

                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recyclerView.setAdapter(adapter);


        //////////////////////////////////////////////////////////////////////////////



        model2= new ArrayList<>();

        root2=FirebaseDatabase.getInstance();
        ref2=root2.getReference("admin/sold");

        adapter2=new SoldPetAdminAdapter(model2,AvailablePet.this);

        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot Snapshot) {
                model2.clear();
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

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recyclerView2.setAdapter(adapter2);




        //////////////////////////////////////////////////////////////////////////////////


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AvailablePet.this, AddPet.class));
            }
        });
    }
}