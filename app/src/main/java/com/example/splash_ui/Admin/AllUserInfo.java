package com.example.splash_ui.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.example.splash_ui.Adapters.AllUserInfoAdapter;
import com.example.splash_ui.Adapters.SoldPetAdminAdapter;
import com.example.splash_ui.ModelClass.available_pet_Admin;
import com.example.splash_ui.PetSection.purchased_pets;
import com.example.splash_ui.R;
import com.example.splash_ui.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AllUserInfo extends AppCompatActivity {

    private String username;
    private RecyclerView recyclerView2;
    private AllUserInfoAdapter adapter2;


    private FirebaseDatabase root2;
    private DatabaseReference ref2;
    private List<User> model2;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_user_info);




        recyclerView2=findViewById(R.id.rec1);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));




        model2= new ArrayList<>();

        root2=FirebaseDatabase.getInstance();
        ref2=root2.getReference("users");

        adapter2=new AllUserInfoAdapter(model2, AllUserInfo.this);




        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot Snapshot) {
                model2.clear();
                if (Snapshot.exists()) {
                    for (DataSnapshot snapshot : Snapshot.getChildren()) {
                        User user = snapshot.getValue(User.class);




                        model2.add(user);
                    }
                    adapter2.notifyDataSetChanged();

                }
                else{
                    Toast.makeText(AllUserInfo.this, "No Customer Yet", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recyclerView2.setAdapter(adapter2);



    }
}

