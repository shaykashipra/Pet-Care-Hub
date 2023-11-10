package com.example.splash_ui.Clinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.splash_ui.R;
import com.google.android.material.card.MaterialCardView;

public class UserActivity extends AppCompatActivity {
    private MaterialCardView appointmentsCard,appointments_all,treatmentscard;
    String user,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        appointmentsCard = findViewById(R.id.appointmentsCard);
        appointments_all = findViewById(R.id.appointments_all);
        treatmentscard = findViewById(R.id.treatmentscard);

        Intent intent=getIntent();
        user=intent.getStringExtra("user");
        pass=intent.getStringExtra("pass");

        appointmentsCard.setOnClickListener(v -> {
            Intent i = new Intent(UserActivity.this, Appointments.class);
            i.putExtra("user",user);
            i.putExtra("pass",pass);
            startActivity(i);
        });

        appointments_all.setOnClickListener(v -> {
            Intent i = new Intent(UserActivity.this, Appoinments_all.class);
            i.putExtra("user",user);
            i.putExtra("pass",pass);
            startActivity(i);

        });

        treatmentscard.setOnClickListener(v -> {
            Intent i = new Intent(UserActivity.this, Treatment.class);
            i.putExtra("user",user);
            i.putExtra("pass",pass);
            startActivity(i);

        });

    }
}