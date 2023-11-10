package com.example.splash_ui.Adapters;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splash_ui.ModelClass.Upload;
import com.example.splash_ui.ModelClass.available_pet_Admin;
import com.example.splash_ui.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class availablePetAdminAdapter extends RecyclerView.Adapter<availablePetAdminAdapter.MyViewHolder> {
    private List<available_pet_Admin> mList;
    private Context context;

    public availablePetAdminAdapter(List<available_pet_Admin> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }


    @NonNull
    @Override
    public availablePetAdminAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.pet_cardview_admin,parent,false);
        return new availablePetAdminAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull availablePetAdminAdapter.MyViewHolder holder, int position) {
        holder.setData(mList.get(position).getAnimal_name(),mList.get(position).getAnimal_price());
        Picasso.get().load(mList.get(position).getImageUrl()).fit().centerCrop().into(holder.AnimalImage);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder{

        private TextView AnimalName,AnimalPrice;
        private Button remove;
        ImageView AnimalImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

           AnimalName = itemView.findViewById(R.id.tv1);
          AnimalPrice = itemView.findViewById(R.id.tv3);
          AnimalImage = itemView.findViewById(R.id.iv1);


            itemView.findViewById(R.id.remove_animal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position=getAdapterPosition();
                if (position != RecyclerView.NO_POSITION ) {

                    String name =mList.get(position).getAnimal_name();

                    FirebaseDatabase.getInstance().getReference("admin/uploads/" + name).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                    };

                }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
                    mList.remove(position);
                    notifyItemRemoved(position);
                }
        };









})

        ;}

        private void setData(String name,Double price){
            AnimalName.setText(name);
            AnimalPrice.setText(String.valueOf(price));


        }

    }}