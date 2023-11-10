package com.example.splash_ui.Reception;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.splash_ui.R;

public class CalculateBill extends AppCompatActivity {

    private EditText treatmentid;
    private EditText basic_charges,treatmentcharges,totalcharges;
    private Button submitbill;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_bill);

        treatmentid = findViewById(R.id.treatmentid);

        basic_charges = findViewById(R.id.basic_charges);
        treatmentcharges = findViewById(R.id.treatmentcharges);

        totalcharges = findViewById(R.id.totalcharges);
        submitbill= findViewById(R.id.submitbill);

        basic_charges.setText("250");
        basic_charges.setEnabled(false);


        submitbill.setOnClickListener(v->{
            if (TextUtils.isEmpty(treatmentid.getText().toString())){
                Toast.makeText(this, "Enter Treatment ID", Toast.LENGTH_SHORT).show();
            }

            else if (TextUtils.isEmpty(treatmentcharges.getText().toString())){
                Toast.makeText(this, "Enter Treatment Charges", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Submitted", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
