package com.example.splash_ui.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.splash_ui.ClinicDoctor.DoctorActivity;
import com.example.splash_ui.ClinicDoctor.GeneratePrescription;
import com.example.splash_ui.ClinicDoctor.PatientAppointments;
import com.example.splash_ui.Login;
import com.example.splash_ui.R;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminActivity extends AppCompatActivity {


    private MaterialCardView petAddcard,Alluser;

    ImageView logout;

    TextView bkash;


    private String user,pass;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        petAddcard = findViewById(R.id.petAddCard);
        Alluser = findViewById(R.id.AlluserCardView);

        bkash=findViewById(R.id.total);

        FirebaseDatabase.getInstance().getReference("bkash/01915609241/balance").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    bkash.setText("BDT:    "+snapshot.getValue(Double.class).toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        logout=findViewById(R.id.logoutadmin);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(AdminActivity.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });


        Intent intent=getIntent();
        user=intent.getStringExtra("username");
        pass=intent.getStringExtra("password");


        petAddcard.setOnClickListener(v->{
            Intent i = new Intent(this, AvailablePet.class);
            i.putExtra("username",user);
            i.putExtra("password",pass);
            startActivity(i);
        });

       Alluser.setOnClickListener(v->{
            Intent i = new Intent(this, AllUserInfo.class);
//            i.putExtra("username",user);
//            i.putExtra("password",pass);
            startActivity(i);
      });



    }
}