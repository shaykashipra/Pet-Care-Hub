package com.example.splash_ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class SignUp extends AppCompatActivity {
    private Button btn_signup,signin;
    private TextInputLayout full_name,username,email,phone,password;
    private FirebaseDatabase root;
    private DatabaseReference ref;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //this line just to hide action bar,status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_sign_up);

        btn_signup=findViewById(R.id.btn_signup);
        signin=findViewById(R.id.signin);
        full_name=findViewById(R.id.full_name);
        username=findViewById(R.id.username);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        password=findViewById(R.id.password);

        root=FirebaseDatabase.getInstance();
        ref=root.getReference("users");

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this, Login.class));
            }
        });


 //sign up button onclick listener add
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validation_name() | !validation_username() | !validation_email() | !validation_phone() | !validation_password()) {
                    return;
                }

                isUser();

            }
        });
    }
//full name
    private Boolean validation_name(){
        String valid_name=full_name.getEditText().getText().toString();
        if(valid_name.isEmpty()){
            full_name.requestFocus();
            full_name.setError("field can't be empty");
            return false;
        }
        else{
            full_name.setError(null);
            full_name.setErrorEnabled(false);
            return true;
        }

    }


//username
    private Boolean validation_username(){
        String valid_user=username.getEditText().getText().toString();
        String nowhitespace="\\A\\w(4,10)\\z";//for checking whitespace
        //if not work nowhitespoace="\\A\\w(4,10)\\z";
        if(valid_user.isEmpty()){
           /* username.requestFocus();
            username.setErrorEnabled(true);*/
            username.setError("field can't be empty");
            return false;
        }

        else if(valid_user.charAt(0)!='@'){
           /* username.requestFocus();
            username.setErrorEnabled(true);*/
            username.setError("1st letter must contain @");

            return false;
        }

        else if(valid_user.charAt(1)>='0'&&valid_user.charAt(1)<='9'){
           username.requestFocus();
         /*   username.setErrorEnabled(true);*/
            username.setError("1st letter must contain @");

            return false;
        }

        else if(valid_user.length()<4){
            username.requestFocus();
            username.setError("Username contains at least 4 characters");

            return false;
        }
        else if(valid_user.length()>15){
            username.requestFocus();
            username.setError("Username contains maximum 15 characters");

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
//Email Address
    private Boolean validation_email(){
        String valid_email=email.getEditText().getText().toString();
        String emailpattern="[a-zA-Z0-9,_-]+@[a-z]+\\.+[a-z]+";//regex expression
        if(valid_email.isEmpty()){
            email.requestFocus();
            email.setError("field can't be empty");
            return false;
        }
        else if(!valid_email.matches(emailpattern)){
            email.requestFocus();
            email.setError("Invalid Email Adress");
            return false;
        }
        else{
            email.requestFocus();
            email.setError(null);
            return true;
        }

    }
//phone num
    private Boolean validation_phone(){
        String valid_phone=phone.getEditText().getText().toString();
        if(valid_phone.isEmpty()){
            phone.requestFocus();
            phone.setError("field can't be empty");
            return false;
        }
        else{
            phone.requestFocus();
            phone.setError(null);
            return true;
        }

    }
//Password
    private Boolean validation_password(){
        String pass=password.getEditText().getText().toString();

        String validpass="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

  /*    "^"  +                 //starting of a string
        "(?=.*[0-9])"+        //at least 1 digit
        "(?=.*[a-z])"+        //at least 1 small letter
        "(?=.*[A-Z])"+        //at least 1 capital letter
        "(?=.*[a-zA-Z])"+     //any letter
        "(?=.*[@#$%^&+=])"+   //at least 1 special character
        "(?=\\s+$)"+          // no white space
        ".{4,}"+              //at least 4 character
        "$"                   //end of a string


   */


        if(pass.isEmpty()){
            password.requestFocus();
            password.setError("field can't be empty");
            return false;
        }
        else if(pass.matches(validpass)) {
            password.requestFocus();
            password.setError(null);
            return true;
        }
        else{
                password.requestFocus();
                password.setError("password is two week");
                return false;
            }


    }

    private void isUser(){
        //final is must here
        final String valuser=username.getEditText().getText().toString();



        Query check=ref.orderByChild("username").equalTo(valuser);

        check.addListenerForSingleValueEvent(new ValueEventListener() {
                                                 @Override
                                                 public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                     //  check if there is some data inside the datasnapshot
                                                     if(snapshot.exists()) {
                                                         username.setError("Username Exists.Try Unique One");



                                                     }

                                                     else{

                                                         username.setError(null);
                                                         username.setErrorEnabled(false);


                                                         String name = full_name.getEditText().getText().toString();
                                                         String user = username.getEditText().getText().toString();
                                                         String email_add = email.getEditText().getText().toString();
                                                         String phn = phone.getEditText().getText().toString();
                                                         String pass = password.getEditText().getText().toString();

                                                         User users = new User(name, user, email_add, phn, pass);
                                                         ref.child(user).setValue(users);

                                                         //go to profile page
                                                         Intent intent = new Intent(SignUp.this, profile.class);
                                                         intent.putExtra("name", name);
                                                         intent.putExtra("email", email_add);
                                                         intent.putExtra("username", user);
                                                         intent.putExtra("phone", phn);
                                                         intent.putExtra("password", pass);
                                                         startActivity(intent);


                                                     }

                                                 }

                                                 @Override
                                                 public void onCancelled(@NonNull DatabaseError error) {

                                                 }
                                             }

        );



    }


}

