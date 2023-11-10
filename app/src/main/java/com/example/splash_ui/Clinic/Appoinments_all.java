package com.example.splash_ui.Clinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Appoinments_all extends AppCompatActivity {

    private RecyclerView appointment_all_user_recycler;
    String user,pass;

    private FirebaseDatabase root;
    private DatabaseReference ref;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoinments_all);

        Intent intent=getIntent();
        user=intent.getStringExtra("user");
        pass=intent.getStringExtra("pass");


        appointment_all_user_recycler = findViewById(R.id.appointments_all_user_recycler);
        appointment_all_user_recycler.setLayoutManager(new LinearLayoutManager(this));
        List<Appointment_User> model = new ArrayList<>();


        root=FirebaseDatabase.getInstance();
        ref=root.getReference("users");

        AppointmentsAllUserAdapter adapter  = new AppointmentsAllUserAdapter(model,this);

        ref.child(user).child("appointment").child("21419").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               if(snapshot.exists()) {
                    String datedb = snapshot.child("date").getValue(String.class);
                    String _doctoriddb = snapshot.child("_doctorid").getValue(String.class);
                    String doctornamedb = snapshot.child("doctor").getValue(String.class);

                    String timedb = snapshot.child("time").getValue(String.class);


                    model.add(new Appointment_User(doctornamedb, timedb, datedb, _doctoriddb, user));

                }
                    adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ref.child(user).child("appointment").child("21420").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String datedb = snapshot.child("date").getValue(String.class);
                    String _doctoriddb = snapshot.child("_doctorid").getValue(String.class);
                    String doctornamedb = snapshot.child("doctor").getValue(String.class);

                    String timedb = snapshot.child("time").getValue(String.class);



                    model.add(new Appointment_User(doctornamedb, timedb, datedb, _doctoriddb, user));

                }

                adapter.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("error");
            }
        });


        appointment_all_user_recycler.setAdapter(adapter);







    }
}