package com.example.splash_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.splash_ui.Clinic.UserActivity;
import com.example.splash_ui.Emergency.Emergency_activity;
import com.example.splash_ui.PetSection.petShop;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class profile extends AppCompatActivity {
   private Button Update_btn,Logout;
    private TextInputLayout full_name,password,email,phone;

   private TextView username_view,name_view,username;
   private MaterialCardView shop_view,clinic_view,emergency_view;
    String user,pass,name2,emailaddress,phonenum;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //hook

        full_name=findViewById(R.id.full_name);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        Update_btn=findViewById(R.id.update);
        Logout=findViewById(R.id.Logout);

        username_view=findViewById(R.id.username_view);
        name_view=findViewById(R.id.name);

        shop_view=findViewById(R.id.shop_view);

        clinic_view=findViewById(R.id.clinic_view);

        emergency_view=findViewById(R.id.Emergency_view);



        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(profile.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });

        //extract info from login page


        Intent intent=getIntent();
        if(intent!=null) {
//must not add .toString() here ,it causes error here
             name2 = intent.getStringExtra("name");
             user = intent.getStringExtra("username");
             emailaddress = intent.getStringExtra("email");
             pass = intent.getStringExtra("password");
            phonenum = intent.getStringExtra("phone");

          if(name2!=null) { full_name.getEditText().setText(name2);}
            username.setText(user);
            password.getEditText().setText(pass);
            email.getEditText().setText(emailaddress);
            phone.getEditText().setText(phonenum);

            username_view.setText(user);
            name_view.setText(name2);

        }
        else{

        }

        Update_btn.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {

                                              ////check valodity////////////////////

                                              if(!validation_name()|!validation_email()|!validation_phone()| !validation_password()) {
                                                  return;
                                              }
                                              /////////////////////////////////////////////////update////////////////
                                              DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users/" + user);

                                              ref.child("name").setValue(full_name.getEditText().getText().toString());
                                              ref.child("password").setValue(password.getEditText().getText().toString());
                                              ref.child("phone").setValue(phone.getEditText().getText().toString());
                                              ref.child("email").setValue( email.getEditText().getText().toString());
                                              Toast.makeText(profile.this,"Update successfull",Toast.LENGTH_SHORT).show();
                                          }
                                      }



);





        //set on click listener

        shop_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(profile.this, petShop.class);
                i.putExtra("user",user);
                startActivity(i);


            }
        });




        clinic_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(profile.this, UserActivity.class);

                i.putExtra("user",user);
                i.putExtra("pass",pass);
                startActivity(i);

            }
        });

        emergency_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(profile.this, Emergency_activity.class);

                i.putExtra("user",user);
                i.putExtra("pass",pass);
                startActivity(i);
            }
        });



    }
   //validate username

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



    //validate password

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


}