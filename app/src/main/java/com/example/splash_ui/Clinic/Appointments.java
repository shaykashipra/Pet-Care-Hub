package com.example.splash_ui.Clinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.splash_ui.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class Appointments extends AppCompatActivity {
    private Spinner doctorspinner,DateSpinner,TimeSpinner;
    private TextView doctorid;
    private Button bookappointment;
    private String doctor,time,date,_doctorid;

    private FirebaseDatabase root;
    private DatabaseReference ref;
    String user,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        doctorspinner = findViewById(R.id.doctorspinner);
        DateSpinner = findViewById(R.id.DateSpinner);
        TimeSpinner = findViewById(R.id.TimeSpinner);
        doctorid = findViewById(R.id.doctorid);
        bookappointment = findViewById(R.id.bookappointment);

        root=FirebaseDatabase.getInstance();
        ref=root.getReference();

        Intent intent=getIntent();
        user=intent.getStringExtra("user");
        pass=intent.getStringExtra("pass");

        List<String> doctors = new ArrayList<>();
        doctors.add("Select Doctor");
        doctors.add("Dr. Raonok");
        doctors.add("Dr. Alia");


        List<String> dates = new ArrayList<>();
        dates.add("Select Date");
        dates.add("21 October 2023");
        dates.add("22 October 2023");
        dates.add("23 October 2023");
        dates.add("24 October 2023");
        dates.add("25 October 2023");
        dates.add("26 October 2023");

        List<String> times = new ArrayList<>();
        times.add("Select Time");
        times.add("9:00 AM - 9:15 AM");
        times.add("9:15 AM - 9:30 AM");
        times.add("9:30 AM - 9:45 AM");

        doctorid.setText("-");

        ArrayAdapter<String> doctoradapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,doctors);
        doctorspinner.setAdapter(doctoradapter);

        ArrayAdapter<String> dateadapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,dates);
        DateSpinner.setAdapter(dateadapter);

        ArrayAdapter<String> timeadapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,times);
        TimeSpinner.setAdapter(timeadapter);

        doctorspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1: doctorid.setText("21419");
                        doctor = "Dr. Raonok";
                        Log.e(doctor,doctorid.getText().toString());
                        _doctorid = "21419";
                        break;
                    case 2: doctorid.setText("21420");
                        doctor = "Dr. Alia";
                        Log.e(doctor,doctorid.getText().toString());
                        _doctorid = "21420";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        DateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 1:
                        date = "21 October 2023";
                        break;
                    case 2:
                        date = "22 October 2023";
                        break;
                    case 3:
                        date = "23 October 2023";
                        break;
                    case 4:
                        date = "24 October 2023";
                        break;
                    case 5:
                        date = "25 October 2023";
                        break;
                    case 6:
                        date = "26 October 2023";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        TimeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        time = "9:00 AM - 9:15 AM";
                        break;
                    case 2:
                        time = "9:15 AM - 9:30 AM";
                        break;
                    case 3:
                        time = "9:30 AM - 9:45 AM";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        bookappointment.setOnClickListener(v -> {
            if (doctorspinner.getSelectedItem().equals("Select Doctor")){
                Toast.makeText(this, "Select Doctor", Toast.LENGTH_SHORT).show();
            }
            else if(TimeSpinner.getSelectedItem().equals("Select Time")){
                Toast.makeText(this, "Select Time", Toast.LENGTH_SHORT).show();
            }
            else if (DateSpinner.getSelectedItem().equals("Select Date")){
                Toast.makeText(this, "Select Date", Toast.LENGTH_SHORT).show();
            }
            else{
                User2 user2=new User2(doctor,time,date,_doctorid);
                ref.child("users").child(user).child("appointment").child(_doctorid).setValue(user2);
                if(doctor.equals("Dr. Raonok"))
                ref.child("doctor").child("@raonok123").child("patients").child(user).setValue(user);
              else if(doctor.equals("Dr. Alia"))
                    ref.child("doctor").child("@trivia1").child("patients").child(user).setValue(user);


                Toast.makeText(this, "Booked", Toast.LENGTH_SHORT).show();
            }
        });






    }
}
