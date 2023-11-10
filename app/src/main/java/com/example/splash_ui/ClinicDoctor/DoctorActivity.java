package com.example.splash_ui.ClinicDoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.splash_ui.Login;
import com.example.splash_ui.R;
import com.example.splash_ui.profile;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;

public class DoctorActivity extends AppCompatActivity {

    private MaterialCardView appointmentsCard,assignlabtestcard;

    private String user,pass;

    ImageView logout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);


        appointmentsCard = findViewById(R.id.appointmentsCard);
        assignlabtestcard = findViewById(R.id.assignlabtestcard);

        logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(DoctorActivity.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });


        Intent intent=getIntent();
         user=intent.getStringExtra("username");
      pass=intent.getStringExtra("password");


        appointmentsCard.setOnClickListener(v->{
            Intent i = new Intent(this,GeneratePrescription.class);
            i.putExtra("username",user);
            i.putExtra("password",pass);
            startActivity(i);
        });

        assignlabtestcard.setOnClickListener(v->{
            Intent i = new Intent(this,PatientAppointments.class);
            i.putExtra("username",user);
            i.putExtra("password",pass);
            startActivity(i);
        });



    }
}
