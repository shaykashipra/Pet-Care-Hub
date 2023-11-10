package com.example.splash_ui.Bkash;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.splash_ui.R;

public class bkash extends AppCompatActivity {
    private EditText account,password,amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bkash);
    }
}