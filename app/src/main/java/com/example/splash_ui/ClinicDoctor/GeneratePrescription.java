package com.example.splash_ui.ClinicDoctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.splash_ui.Login;
import com.example.splash_ui.R;
import com.example.splash_ui.profile;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GeneratePrescription extends AppCompatActivity {
    private TextView treatmentIdPrescription,doctorsNamePrescription;
    private EditText PatientIdPrescription,Date;
    private ListView recyclerPrescription;
    private Button addButton,submit;

    private TextInputLayout prescriptionlayout;

    private FirebaseDatabase root;
    private DatabaseReference ref;
    private String user,pass;

    List<String> medlist = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_prescription);


        recyclerPrescription = findViewById(R.id.recyclerPrescription);

        doctorsNamePrescription = findViewById(R.id.doctorsNamePrescription);
        PatientIdPrescription = findViewById(R.id.PatientIdPrescription);
        prescriptionlayout=findViewById(R.id.prescriptionlayout);
        Date=findViewById(R.id.datePrescription);

        addButton = findViewById(R.id.addButton);
        submit = findViewById(R.id.submit);

        root=FirebaseDatabase.getInstance();
        ref=root.getReference();

        Intent intent =getIntent();
        user=intent.getStringExtra("username");
       pass=intent.getStringExtra("password");


        if("@raonok123".equals(user)){
            doctorsNamePrescription.setText("Dr. Raonok");


        }
        else if("@trivia1".equals(user)){
            doctorsNamePrescription.setText("Dr. Alia");
        }

        submit.setOnClickListener(v -> {
            if (!medlist.isEmpty()&&!PatientIdPrescription.getText().toString().isEmpty()&&!Date.getText().toString().isEmpty()){
                if (!TextUtils.isEmpty(PatientIdPrescription.getText().toString())){
                     isUser();


                    Intent i = new Intent(this,DoctorActivity.class);
                    Toast.makeText(this, "Prescription Generated", Toast.LENGTH_SHORT).show();

                    startActivity(i);
                }
                else {
                    Toast.makeText(this, "enter all details", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "add medicines", Toast.LENGTH_SHORT).show();
            }
        });


        addButton.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view  = LayoutInflater.from(getApplicationContext()).inflate(R.layout.alertdialog_add_medicine_doctor,null);
            Button ok = view.findViewById(R.id.addmedicine);
            EditText medname = view.findViewById(R.id.medicinename);
            ok.setOnClickListener(v1 -> {
                if (!TextUtils.isEmpty(medname.getText().toString())){

                    medlist.add(medname.getText().toString());
                    Log.e("MEDLIST",medname.getText().toString());
                    setupRecycler(medlist);

                }
            });

            builder.setView(view);
            builder.show();
        });

       // Long timestamp = System.currentTimeMillis();






    }

    private void setupRecycler(List<String> medlist) {

        for (String a: medlist){
            Log.e("VAL",a);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,medlist);
        recyclerPrescription.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void PostPatientData(String toString) {

        // api call
    }



    private void isUser() {
        //final is must here
        final String valuser = PatientIdPrescription.getText().toString();


        Query check = ref.child("users").orderByChild("username").equalTo(valuser);

        check.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //  check if there is some data inside the datasnapshot
                if (snapshot.exists()) {
                    PatientIdPrescription.setError(null);


                    String comment=prescriptionlayout.getEditText().getText().toString();
String date=Date.getText().toString();
//                    ref.child("users").child(valuser).child("prescription").child("comment").setValue(comment);
//                    ref.child("users").child(valuser).child("prescription").child("medicine").setValue(medlist);
//                    ref.child("users").child(valuser).child("prescription").child("date").setValue(date);
                   if("@raonok123".equals(user)){

                        ref.child("users").child(valuser).child("prescription1").child("doctor").setValue(doctorsNamePrescription.getText().toString());

                    ref.child("users").child(valuser).child("prescription1").child("comment").setValue(comment);
                    ref.child("users").child(valuser).child("prescription1").child("medicine").setValue(medlist);
                    ref.child("users").child(valuser).child("prescription1").child("date").setValue(date);

                }
                    else if("@trivia1".equals(user)){
                        ref.child("users").child(valuser).child("prescription2").child("doctor").setValue(doctorsNamePrescription.getText().toString());
                    ref.child("users").child(valuser).child("prescription2").child("comment").setValue(comment);
                    ref.child("users").child(valuser).child("prescription2").child("medicine").setValue(medlist);
                    ref.child("users").child(valuser).child("prescription2").child("date").setValue(date);

                }


                }
                else{
                    Toast.makeText(GeneratePrescription.this, "Invalid Patient ID", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }



}