package com.example.splash_ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.splash_ui.About.About;
import com.example.splash_ui.Admin.AdminActivity;
import com.example.splash_ui.Admin.admin_login;
import com.example.splash_ui.ClinicDoctor.DoctorActivity;
import com.example.splash_ui.ClinicDoctor.doctor_login;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    private NavigationView navigationView;

    Toolbar toolbar2;
    ActionBarDrawerToggle toggle;
    private Button signup;
    private Button signin,Admin;


    private ImageView iv2,iv3,menu2;
    private TextView tv2,tv3;
    private int id1,id2,id3,id4;

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
        setContentView(R.layout.activity_login);

        topAnim= AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim=AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigation);
        toolbar2=findViewById(R.id.toolbar2);

        signup=findViewById(R.id.signup);
        signin=findViewById(R.id.signin);
        Admin=findViewById(R.id.admin_login);

        iv2=findViewById(R.id.iv2);
        iv3=findViewById(R.id.iv3);
        menu2=findViewById(R.id.menuIcon);


        tv2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv3);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);

        id1=R.id.nav_admin;
        id2=R.id.nav_doctor;
        id3=R.id.nav_user;
        id4=R.id.nav_about;


        root=FirebaseDatabase.getInstance();
        ref=root.getReference("users");

        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

   //........ToolBar as ActionBar............

              setSupportActionBar(toolbar2);


    //..............Navigation Drawer Menu...........
           navigationView.bringToFront();
               toggle=new ActionBarDrawerToggle(Login.this,drawerLayout,toolbar2,R.string.nav,R.string.nav_close);
              drawerLayout.addDrawerListener(toggle);
              toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);




        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, SignUp.class);

                    Pair[] pairs = new Pair[7];

                    pairs[0] = new Pair<View, String>(iv2, "logo_image");
                    pairs[1] = new Pair<View, String>(tv2, "logo_text");
                    pairs[2] = new Pair<View, String>(iv3, "trans_slogan");
                    pairs[3] = new Pair<View, String>(iv2, "trans_username");
                    pairs[4] = new Pair<View, String>(iv2, "trans_pass");
                    pairs[5] = new Pair<View, String>(iv2, "trans_forget");
                    pairs[6] = new Pair<View, String>(iv2, "trans_signup");

                   // ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
                    startActivity(i);

            }
        });

        Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, admin_login.class));
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

                String namedb=snapshot.child(valuser).child("name").getValue(String.class);
                String emaildb=snapshot.child(valuser).child("email").getValue(String.class);
             String userdb=snapshot.child(valuser).child("username").getValue(String.class);
             String phonedb=snapshot.child(valuser).child("phone").getValue(String.class);

             //go to profile page
            Intent intent=new Intent(Login.this, profile.class);
             intent.putExtra("name",namedb);
             intent.putExtra("email",emaildb);
             intent.putExtra("username",userdb);
             intent.putExtra("phone",phonedb);
             intent.putExtra("password",passdb);
            startActivity(intent);

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

    //for back to login activity from navigation drawer


    @Override
    public void onBackPressed() {
        //if drawer is open then after back application will not close but go to login page
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_admin) {


            startActivity(new Intent(Login.this, admin_login.class));

        } else if (item.getItemId() == R.id.nav_doctor) {

            startActivity(new Intent(Login.this, doctor_login.class));

        } else if (item.getItemId() == R.id.nav_about){
            startActivity(new Intent(Login.this, About.class));

    }


        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}