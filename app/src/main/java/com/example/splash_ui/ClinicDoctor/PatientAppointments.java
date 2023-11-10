package com.example.splash_ui.ClinicDoctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.splash_ui.Adapters.AppointmentsAllUserAdapter;
import com.example.splash_ui.Adapters.PatientAppointmentsDoctorAdapter;
import com.example.splash_ui.ModelClass.Appointment_User;
import com.example.splash_ui.ModelClass.PatientAppointments_Doctor;
import com.example.splash_ui.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PatientAppointments extends AppCompatActivity {

    private RecyclerView appointment_all_user_recycler;
    String user,pass,key,patient;
    List<PatientAppointments_Doctor> model = new ArrayList<>();
    private FirebaseDatabase root,root2;
    private DatabaseReference ref,ref2;

    private ImageView minus;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_appointments);

        minus=findViewById(R.id.minus);

        Intent intent=getIntent();
        user=intent.getStringExtra("username");
        pass=intent.getStringExtra("password");
        if(user!=null) {
            if (user.equals("@raonok123")) {
                key = "21419";
            } else if (user.equals("@trivia1")) {
                key = "21420";
            }
        }
        appointment_all_user_recycler = findViewById(R.id.appointments_all_user_recycler);
        appointment_all_user_recycler.setLayoutManager(new LinearLayoutManager(this));



        root=FirebaseDatabase.getInstance();
        ref=root.getReference("doctor/"+user+"/patients");

        root2=FirebaseDatabase.getInstance();
        ref2=root2.getReference("users");

        PatientAppointmentsDoctorAdapter adapter  = new PatientAppointmentsDoctorAdapter(model,this);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot patientsnap : snapshot.getChildren()) {
                     patient = patientsnap.getValue(String.class);



                    ref2.child(patient).child("appointment").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                      @Override
                      public void onDataChange(@NonNull DataSnapshot snapshot) {
                         if(snapshot.exists()){
                             String datedb = snapshot.child("date").getValue(String.class);

                             String doctornamedb = snapshot.child("doctor").getValue(String.class);

                             String timedb = snapshot.child("time").getValue(String.class);

                             model.add(new PatientAppointments_Doctor(datedb,doctornamedb,patient,timedb));
                             adapter.notifyDataSetChanged();
                         }

                      }

                      @Override
                      public void onCancelled(@NonNull DatabaseError error) {

                      }
                  });


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

          appointment_all_user_recycler.setAdapter(adapter);



    }
}