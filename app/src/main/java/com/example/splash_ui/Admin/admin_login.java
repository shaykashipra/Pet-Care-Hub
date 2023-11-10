package com.example.splash_ui.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.splash_ui.Login;
import com.example.splash_ui.R;
import com.example.splash_ui.SignUp;
import com.example.splash_ui.profile;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class admin_login extends AppCompatActivity {


    private Button signin,User;
    private ImageView iv2,iv3;
    private TextView tv2,tv3;

    private Animation topAnim,bottomAnim;
    private TextInputLayout username,password;

    private FirebaseDatabase root;
    private DatabaseReference ref;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this line just to hide action bar,status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_admin_login);

        topAnim= AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim=AnimationUtils.loadAnimation(this, R.anim.bottom_animation);


        signin=findViewById(R.id.signin);
        User=findViewById(R.id.user_login);

        iv2=findViewById(R.id.iv2);
        iv3=findViewById(R.id.iv3);

        tv2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv3);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);

        root=FirebaseDatabase.getInstance();
        ref=root.getReference("admin");







        User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(admin_login.this, Login.class));
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!validusername()&&!validpassword()){
                    return;
                }

                else{
                    isUser();
                }
            }
        });


    }

    private void isUser(){
        //final is must here
        final String valuser=username.getEditText().getText().toString();
        final String valpass=password.getEditText().getText().toString();


        Query check=ref.orderByChild("username").equalTo(valuser);

        check.addListenerForSingleValueEvent(new ValueEventListener() {
                                                 @Override
                                                 public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                     //  check if there is some data inside the datasnapshot
                                                     if(snapshot.exists()) {
                                                         username.setError(null);
                                                         username.setErrorEnabled(false);

                                                         password.setError(null);
                                                         password.setErrorEnabled(false);



                                                         //if exists then retrive data
                                                         //User user=snapshot.child("users").child(valuser).getValue(User.class);

                                                         String passdb=snapshot.child(valuser).child("password").getValue(String.class);
                                                         System.out.println(passdb);
                                                         if(passdb.equals(valpass)){

                                                             Intent i=new Intent(admin_login.this,AdminActivity.class);
                                                                 i.putExtra("username",valuser);
                                                                 i.putExtra("password",valpass);
                                                             startActivity(i);

                                                         }

                                                         else{
                                                             password.setError("Wrong password!");
                                                         }

                                                     }

                                                     else{
                                                         username.setError("Username doesnot exist");

                                                     }

                                                 }

                                                 @Override
                                                 public void onCancelled(@NonNull DatabaseError error) {

                                                 }
                                             }

        );



    }

    private Boolean validusername(){
        String valid_user=username.getEditText().getText().toString();
        String nowhitespace="\\A\\w(4,10)\\z";//for checking whitespace
        //if not work nowhitespoace="\\A\\w(4,10)\\z";
        if(valid_user.isEmpty()){
            username.requestFocus();
            /*username.setErrorEnabled(true);*/
            username.setError("field can't be empty");
            return false;
        }

        else if(valid_user.charAt(0)!='@'){
            username.requestFocus();
            /* username.setErrorEnabled(true);*/
            username.setError("1st letter must contain @");

            return false;
        }

        else if(valid_user.charAt(1)>='0'&&valid_user.charAt(1)<='9'){
            username.requestFocus();
            /*  username.setErrorEnabled(true);*/
            username.setError("1st letter must contain @");

            return false;
        }

        else if(valid_user.length()<4){
            username.setError("Username contains at least 4 characters");

            return false;
        }

        else if(valid_user.matches(" ")){
           /* username.requestFocus();
            username.setErrorEnabled(true);*/
            username.setError("white spaces are not allowed");

            return false;
        }
        else{
            /*username.requestFocus();*/
            username.setError(null);
            username.setErrorEnabled(false);//will remove the space and set out error
            return true;
        }

    }

    private Boolean validpassword(){
        String val=password.getEditText().getText().toString();
        if(val.isEmpty()){

            password.setError("Field can't be empty");
            return false;

        }
        else{

            password.setError(null);
            password.setErrorEnabled(false);
            return true;


        }
    }
}