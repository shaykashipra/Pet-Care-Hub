package com.example.splash_ui.Emergency;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.splash_ui.R;

public class PetCareWeb extends AppCompatActivity {
    private WebView web;

    @Override
    public void onBackPressed() {
        if(web.canGoBack()) {
            web.goBack();
        }
        else{
            super.onBackPressed();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_care_web);
        web = findViewById(R.id.web);
        web.setWebViewClient(new WebViewClient());
        web.loadUrl("https://www.evanspharmacy.com/pet-medicines-and-food-c1/prescription-only-medicines-c15");

        WebSettings  webSettings=web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        }

    }
