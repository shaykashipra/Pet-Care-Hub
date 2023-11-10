package com.example.splash_ui.Admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.splash_ui.ModelClass.Upload;
import com.example.splash_ui.R;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class AddPet extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST=1;

    private String name,description;
    double BDT;

    private Button choose,upload;

    private ImageView preview_img,home;

    private TextInputLayout animal_name,animal_description,price;

    private TextInputEditText editdescription;



    ProgressBar progressBar;

    private Uri imageuri;

    private StorageReference storageReference;
    private DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);

        //hook...............
        progressBar=findViewById(R.id.progress_bar);
        choose=findViewById(R.id.choose_btn);
        upload=findViewById(R.id.btn_upload);

        preview_img=findViewById(R.id.preview_img);
        home=findViewById(R.id.admin_act);

        animal_name=findViewById(R.id.Animal_name);
        animal_description=findViewById(R.id.Animal_Description);
        price=findViewById(R.id.Animal_Price);


        editdescription=findViewById(R.id.edit_description);


        storageReference= FirebaseStorage.getInstance().getReference("admin/uploads");
        databaseReference=FirebaseDatabase.getInstance().getReference("admin/uploads");
        //extract string
        editdescription.setSingleLine(false);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddPet.this,AdminActivity.class));
            }
        });





        //choose btn listener...........

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                openfilechooser();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                if(!validAnimalName()|!validAnimalDescription()|!validPrice()) {



                            return;


                }
                    uploadfile();

            }
        });



    }

    private String getFileExtension(Uri uri){
        //retrive the extension part
        ContentResolver CR=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return  mime.getExtensionFromMimeType(CR.getType(uri));
    }

    private void uploadfile() {
        name=animal_name.getEditText().getText().toString();
        description=animal_description.getEditText().getText().toString();

        try {
             BDT = Double.parseDouble(price.getEditText().getText().toString());
            price.setError(null);
        } catch (NumberFormatException e) {
            price.setError("field can't be empty");

        }
        if(imageuri!=null){

            StorageReference filereference=storageReference.child(name+"."+getFileExtension(imageuri));
            filereference.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(0);
                        }
                    },500);
                  //  Toast.makeText(AddPet.this,"Image Uploaded Successfully",Toast.LENGTH_SHORT).show();
                  //  Upload up=new Upload(name,description,BDT,taskSnapshot.getStorage().getDownloadUrl().toString());
                  // String uploadID=databaseReference.push().getKey();
                  // databaseReference.child(name).setValue(up);
                    filereference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String downloadUrl = uri.toString();


                            Upload up = new Upload(name, description, BDT, downloadUrl);

                            databaseReference.child(name).setValue(up);

                            Toast.makeText(AddPet.this, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AddPet.this, AvailablePet.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddPet.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(AddPet.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
           double progress=(100.0*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
           progressBar.setProgress((int) progress);
                }
            });
        }
        else{
            Toast.makeText(this,"No File Selevted",Toast.LENGTH_SHORT).show();
        }
    }

    private void openfilechooser() {
        Intent intent=new Intent();
        intent.setType("image/*");//so that we can only choose image
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST); //private static final int PICK_IMAGE_REQUEST=1;

    }
//this method will be called when we'll picked the file
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK&&data!=null&&data.getData()!=null){
            imageuri=data.getData();
            Picasso.get().load(imageuri).into(preview_img);
            //if we dont want to use picasso
           // preview_img.setImageURI(imageuri);

        }
    }

    private Boolean validAnimalName(){
        if(animal_name.getEditText().getText().toString().isEmpty()){
            //animal_name.requestFocus();

            animal_name.setError("field can't be empty");
            return false;
        }
else{
        animal_name.setError(null);
     //   animal_name.setErrorEnabled(false);
        return true;}
    }

    private Boolean validAnimalDescription(){
        if(animal_description.getEditText().getText().toString().isEmpty()){
          //  animal_description.requestFocus();

            animal_description.setError("field can't be empty");
            return false;
        }
        else {
            animal_description.setError(null);

            return true;
        }
    }

    private Boolean validPrice(){
        if(price.getEditText().getText().toString().isEmpty()){
           // price.requestFocus();

            price.setError("field can't be empty");
            return false;
        }
   else{
        price.setError(null);
      //  price.setErrorEnabled(false);
        return true;}
    }
}

