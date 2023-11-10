package com.example.splash_ui.Clinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.splash_ui.Adapters.AppointmentsAllUserAdapter;
import com.example.splash_ui.Adapters.TreatmentUserAdapter;
import com.example.splash_ui.ModelClass.Appointment_User;
import com.example.splash_ui.ModelClass.Treatment_User;
import com.example.splash_ui.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Treatment extends AppCompatActivity {
    private RecyclerView treatment_user_recycler;

    private FirebaseDatabase root;
    private DatabaseReference ref;
    List<String> medlist=new ArrayList<>();

    String user,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment);

        Intent intent=getIntent();
        user=intent.getStringExtra("user");
        pass=intent.getStringExtra("pass");

        treatment_user_recycler = findViewById(R.id.treatment_user_recycler);
        treatment_user_recycler.setLayoutManager(new LinearLayoutManager(this));
        List<Treatment_User> model = new ArrayList<>();

        root=FirebaseDatabase.getInstance();
        ref=root.getReference("users/"+user);

        TreatmentUserAdapter adapter  = new TreatmentUserAdapter(model,this);



        ref.child("prescription1").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

               // for (DataSnapshot snapshot : Snapshot.getChildren()) {
                if(snapshot.exists()) {
                    String datedb = snapshot.child("date").getValue(String.class);

                    String doctornamedb = snapshot.child("doctor").getValue(String.class);

                    String advicedb = snapshot.child("comment").getValue(String.class);

                   medlist.clear();

                    for (DataSnapshot medicineSnapshot : snapshot.child("medicine").getChildren()) {
                        String medicine = medicineSnapshot.getValue(String.class);
                        medlist.add(medicine);
                    }

                    model.add(new Treatment_User(datedb,doctornamedb, advicedb,medlist));

                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        ref.child("prescription2").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
             //   for (DataSnapshot snapshot : Snapshot.getChildren()) {
                    String datedb = snapshot.child("date").getValue(String.class);

                    String doctornamedb = snapshot.child("doctor").getValue(String.class);

                    String advicedb = snapshot.child("comment").getValue(String.class);

                    medlist.clear();

                    for (DataSnapshot medicineSnapshot : snapshot.child("medicine").getChildren()) {
                        String medicine = medicineSnapshot.getValue(String.class);
                        medlist.add(medicine);
                    }

                    model.add(new Treatment_User(datedb,doctornamedb, advicedb,medlist));

                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        treatment_user_recycler.setAdapter(adapter);


    }
}