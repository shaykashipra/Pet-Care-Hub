package com.example.splash_ui.Emergency;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.splash_ui.Admin.AdminActivity;
import com.example.splash_ui.Login;
import com.example.splash_ui.R;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;

public class Emergency_activity extends AppCompatActivity {
    private MaterialCardView webview,phonecallview,emergencyview;
    private ImageView logout;
    private String user,pass;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        webview=findViewById(R.id.petcarewebview);
        phonecallview=findViewById(R.id.Rescue_Animal_view);
        emergencyview=findViewById(R.id.Emergency_location_view);
        logout=findViewById(R.id.logoutEmergency);

        Intent intent=getIntent();
        user=intent.getStringExtra("user");
        pass=intent.getStringExtra("pass");

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(Emergency_activity.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        webview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           Intent i=new Intent(Emergency_activity.this, PetCareWeb.class);
              startActivity(i);

            }
        });

        phonecallview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

        emergencyview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




            }
        });

    }

    @Override
    public void onBackPressed() {

    }
}