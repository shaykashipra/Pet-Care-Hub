package com.example.splash_ui;

import static com.example.splash_ui.R.*;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int screen=4000;
    //for animation add some variables
    Animation topAnim,bottomAnim;
    ImageView iv1;
    TextView tv1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this line just to hide action bar,status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(layout.activity_main);

        //Animations
        //android.view.animation
        topAnim=AnimationUtils.loadAnimation(this,anim.top_animation);
        bottomAnim=AnimationUtils.loadAnimation(this,anim.bottom_animation);

        iv1=findViewById(R.id.iv1);
        tv1=findViewById(R.id.tv1);

        iv1.setAnimation(topAnim);
        tv1.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
          /*      //without animation
                startActivity(new Intent(MainActivity.this,Login.class));
                finish();*/

                Intent i=new Intent(MainActivity.this,Login.class);
                // required: Activity,android.util.Pair<View,String>[]
                //  found:    MainActivity,androidx.core.util.Pair[]
                //error can happen here
               Pair[] pairs=new Pair[2];
               pairs[0]=new Pair<View,String>(iv1,"logo_image");
               pairs[1]=new Pair<View,String>(tv1,"logo_text");

               ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
               startActivity(i,options.toBundle());
            }

            },screen);


    }
}