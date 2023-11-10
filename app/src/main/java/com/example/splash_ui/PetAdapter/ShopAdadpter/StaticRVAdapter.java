package com.example.splash_ui.PetAdapter.ShopAdadpter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splash_ui.R;

import java.util.ArrayList;

public class StaticRVAdapter extends RecyclerView.Adapter<StaticRVAdapter.staticRVViewHolder>{
     private ArrayList<StaticRVModel>items;
     int row_idx=-1;
     Activity activity;
     boolean select=true,check=true;

     UpdateRecyclerView updateRecyclerView;



    public StaticRVAdapter(ArrayList<StaticRVModel> items, Activity activity, UpdateRecyclerView updateRecyclerView){
         this.items=items;
         this.updateRecyclerView=updateRecyclerView;
         this.activity=activity;
     }
    @NonNull
    @Override
    public staticRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.static_rv_items,parent,false);
        staticRVViewHolder staticrvholder=new staticRVViewHolder(v);
        return staticrvholder;
    }


    @Override
    public void onBindViewHolder(@NonNull staticRVViewHolder holder, @SuppressLint("RecyclerView") final int position) {
       final StaticRVModel currentitem=items.get(position);
       if(currentitem!=null) {
           holder.iv1.setImageResource(currentitem.getImage());
           holder.tv1.setText(currentitem.getText());

           if (check) {
               ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
               items.add(new DynamicRVModel(R.drawable.food_dog_pedigree_small_dog, "PEDIGREE small dog", "250 gm", 100));
               items.add(new DynamicRVModel(R.drawable.food_cat_temptations_chicken_flavour, "Temptation Flavour(Cat food)", "250 gm", 100));
               items.add(new DynamicRVModel(R.drawable.food_bird_wild_bird, "Wild Bird", "250 gm", 100));

               updateRecyclerView.callback(position, items);
               check = false;

           }

           holder.linearLayout.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   row_idx = position;
                   notifyDataSetChanged();

                   if (position == 0) {
                       ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
                       items.add(new DynamicRVModel(R.drawable.food_dog_pedigree_small_dog, "PEDIGREE small dog", "250 gm", 100));
                       items.add(new DynamicRVModel(R.drawable.food_cat_temptations_chicken_flavour, "Temptation Flavour(Cat food)", "250 gm", 100));
                       items.add(new DynamicRVModel(R.drawable.food_bird_wild_bird, "Wild Bird", "250 gm", 50));

                       updateRecyclerView.callback(position, items);

                   } else if (position == 1) {
                       ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
                       items.add(new DynamicRVModel(R.drawable.food_dog_pedigree_small_dog, "Medicine", "250 gm", 20));
                       items.add(new DynamicRVModel(R.drawable.food_cat_temptations_chicken_flavour, "Medicine 2", "250 gm", 30));
                       items.add(new DynamicRVModel(R.drawable.food_bird_wild_bird, "Wild Bird", "250 gm", 25));

                       updateRecyclerView.callback(position, items);
                   } else if (position == 2) {

                       ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
                       items.add(new DynamicRVModel(R.drawable.food_dog_pedigree_small_dog, " small dog", "250 gm", 150));
                       items.add(new DynamicRVModel(R.drawable.food_cat_temptations_chicken_flavour, "Temptation Flavour(Cat food)", "250 gm", 120));
                       items.add(new DynamicRVModel(R.drawable.food_bird_wild_bird, "Wild Bird", "250 gm", 100));

                       updateRecyclerView.callback(position, items);
                   }


               }
           });

           if (select) {
               if (position == 0) {
                   holder.linearLayout.setBackgroundResource(R.drawable.static_rv_selected_bg);
                   select = false;
               }
           }
    else{
           if (row_idx == position) {
               holder.linearLayout.setBackgroundResource(R.drawable.static_rv_selected_bg);
           } else {
               holder.linearLayout.setBackgroundResource(R.drawable.static_rv_bg);
           }
       }
       }
//        if(row_idx==position){
//            holder.linearLayout.setBackgroundResource(R.drawable.static_rv_selected_bg);
//        }
//        else
//        holder.linearLayout.setBackgroundResource(R.drawable.static_rv_bg);
    }

    @Override
    public int getItemCount() {

         return items.size();
    }

    public static  class staticRVViewHolder extends RecyclerView.ViewHolder{
     TextView tv1;
     ImageView iv1;
     LinearLayout linearLayout;
        public staticRVViewHolder(@NonNull View itemView) {
            super(itemView);
            iv1=itemView.findViewById(R.id.pet_food_icon);
            tv1=itemView.findViewById(R.id.pet_food_text);
            linearLayout=itemView.findViewById((R.id.petitems));//from static_rv_items.xml linear layout
        }
    }
}
