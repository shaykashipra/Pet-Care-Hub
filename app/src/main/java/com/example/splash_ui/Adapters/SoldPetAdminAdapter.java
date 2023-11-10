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

import com.example.splash_ui.ModelClass.available_pet_Admin;
import com.example.splash_ui.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SoldPetAdminAdapter extends RecyclerView.Adapter<SoldPetAdminAdapter.MyViewHolder> {
    private List<available_pet_Admin> mList;
    private Context context;

    public SoldPetAdminAdapter(List<available_pet_Admin> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }


    @NonNull
    @Override
    public SoldPetAdminAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.sold_pet_cardview_admin,parent,false);
        return new SoldPetAdminAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SoldPetAdminAdapter.MyViewHolder holder, int position) {
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

      }

        private void setData(String name,Double price){
            AnimalName.setText(name);
            AnimalPrice.setText(String.valueOf(price));


        }

    }}
