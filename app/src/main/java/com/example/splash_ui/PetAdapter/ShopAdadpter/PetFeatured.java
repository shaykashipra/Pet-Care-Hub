
package com.example.splash_ui.PetAdapter.ShopAdadpter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splash_ui.Adapters.availablePetAdminAdapter;
import com.example.splash_ui.Login;
import com.example.splash_ui.ModelClass.Upload;
import com.example.splash_ui.ModelClass.available_pet_Admin;
import com.example.splash_ui.PetSection.PetItemsDetails;
import com.example.splash_ui.R;
import com.example.splash_ui.profile;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PetFeatured extends RecyclerView.Adapter<PetFeatured.MyViewHolder> {

    private List<Upload> mList;
    private Context context;
    String user;
    private AlertDialog.Builder builder;
    public PetFeatured(List<Upload> mList, Context context,String user) {
        this.mList = mList;
        this.context = context;
        this.user=user;
    }


    @NonNull
    @Override
    public PetFeatured.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardviewcategory,parent,false);
        return new PetFeatured.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PetFeatured.MyViewHolder holder, int position) {
        holder.setData(mList.get(position).getName(),mList.get(position).getPrice(),mList.get(position).getDescription());
        Picasso.get().load(mList.get(position).getImageurl()).fit().centerCrop().into(holder.AnimalImage, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder{

        private TextView AnimalName,AnimalPrice,AnimalDescription;

        private Button remove;
        ImageView AnimalImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            AnimalName = itemView.findViewById(R.id.tv1);
            AnimalPrice = itemView.findViewById(R.id.tv3);
            AnimalImage = itemView.findViewById(R.id.iv1);


            itemView.findViewById(R.id.forward).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(context, PetItemsDetails.class);
                    i.putExtra("url",mList.get(getAdapterPosition()).getImageurl());
                    i.putExtra("name",mList.get(getAdapterPosition()).getName());
                    i.putExtra("description",mList.get(getAdapterPosition()).getDescription());
                    i.putExtra("price",mList.get(getAdapterPosition()).getPrice());
                         context.startActivity(i);


                }
            });

               //confirm purchase click

            itemView.findViewById(R.id.tv4).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     View dialogview=LayoutInflater.from(context).inflate(R.layout.activity_bkash, null);
                    EditText account=dialogview.findViewById(R.id.account_num);
                    EditText pin=dialogview.findViewById(R.id.bkash_pin);
                    TextView amount=dialogview.findViewById(R.id.amount);
                    amount.setText(AnimalPrice.getText().toString());

                    TextView btn=dialogview.findViewById(R.id.payment);
                    int position=getAdapterPosition();
                    builder=new AlertDialog.Builder(AnimalName.getContext());
                    builder.setTitle("Confirm Purchase?");
                    builder.setMessage("At first make payment");

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        AlertDialog.Builder    builder = new AlertDialog.Builder(context);

                         //   TextView payment= view.findViewById(R.id.payment);
                            btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    String acnt_num=account.getText().toString();
                                    String bkashpin=pin.getText().toString();
                                     Double payment=mList.get(getAdapterPosition()).getPrice();

                                             BkashPayment(acnt_num,bkashpin,payment,position);



                                }
                            });
                            builder.setView(dialogview);
                            builder.show();

                            /////////////////////////////////////////////



//
//
        //           int position=getAdapterPosition();
//                    if (position != RecyclerView.NO_POSITION) {
//
//                        String name =mList.get(position).getName();
//
//                        FirebaseDatabase.getInstance().getReference("admin/uploads/"+name).addValueEventListener(
//                                new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                        if(snapshot.exists()){
//
//                                            Upload up=snapshot.getValue(Upload.class);
//                                            FirebaseDatabase.getInstance().getReference("admin/sold/"+name).setValue(up);
//                                            FirebaseDatabase.getInstance().getReference("users/"+user+"/cart/"+name).setValue(up);
//
//
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError error) {
//
//                                    }
//                                }
//                        );
//
//                        /////sold item added to user cart/////////
//
//
//                      //////remove data/////////////
//
//
//                        FirebaseDatabase.getInstance().getReference("admin/uploads/" + name).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void unused) {
//
//                            };
//
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//
//                            }
//                        });
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//                        //////////////////////////////////////////////////////////
//
//
//
//
//
//
//
//                    }
                }









            });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.show();

                }
            });



      }



        private void setData(String name,Double price,String description){
            AnimalName.setText(name);
            AnimalPrice.setText(String.valueOf(price));



        }

    }

    private void BkashPayment(String acntNum, String bkashpin, Double payment,int position) {

        isUser(acntNum,bkashpin,payment,position);


    }

    private void isUser(String acntNum, String bkashpin, Double payment,int position) {
        final String phone=acntNum;
        final String pin=bkashpin;
        Query check=FirebaseDatabase.getInstance().getReference("bkash").orderByChild("phone").equalTo(phone);

        check.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {


                    String passdb=snapshot.child(phone).child("password").getValue(String.class);
                    if(passdb.equals(pin)){
                       Double availablebalance=snapshot.child(phone).child("balance").getValue(Double.class);
                       if(availablebalance-payment>=0){
                           FirebaseDatabase.getInstance().getReference("bkash/"+phone+"/balance").setValue(availablebalance-payment);
                           FirebaseDatabase.getInstance().getReference("bkash/01915609241/balance").addListenerForSingleValueEvent(
                                   new ValueEventListener() {
                                       @Override
                                       public void onDataChange(@NonNull DataSnapshot snapshot) {
                                           if(snapshot.exists()){
                                               Double p=snapshot.getValue(Double.class);
                                               FirebaseDatabase.getInstance().getReference("bkash/01915609241/balance").setValue(p+payment)  ;
                                           }
                                       }

                                       @Override
                                       public void onCancelled(@NonNull DatabaseError error) {

                                       }
                                   }
                           );
                           Toast.makeText(context,"Payment Successfull",Toast.LENGTH_SHORT).show();
                           if (builder != null) {

                               builder.create().dismiss();
                           }

                           dissmiss_data(position);

                       }
                       else{
                           Toast.makeText(context,"Not enough money",Toast.LENGTH_SHORT).show();
                           if (builder != null) {

                               builder.create().dismiss();
                           }
                       }

                    }
                    else{
                        Toast.makeText(context,"wrong password",Toast.LENGTH_SHORT).show();

                    }
                }
                else{
                    Toast.makeText(context,"wrong Account number",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void dissmiss_data(int position) {


        if (position != RecyclerView.NO_POSITION && position < mList.size()) {

            String name = mList.get(position).getName();

            FirebaseDatabase.getInstance().getReference("admin/uploads/" + name).addValueEventListener(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {

                                Upload up = snapshot.getValue(Upload.class);
                                FirebaseDatabase.getInstance().getReference("admin/sold/" + name).setValue(up);
                                FirebaseDatabase.getInstance().getReference("users/" + user + "/cart/" + name).setValue(up);


                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    }
            );

            /////sold item added to user cart/////////


            //////remove data/////////////


            FirebaseDatabase.getInstance().getReference("admin/uploads/" + name).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {

                }

                ;

            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });


        }
    }

}