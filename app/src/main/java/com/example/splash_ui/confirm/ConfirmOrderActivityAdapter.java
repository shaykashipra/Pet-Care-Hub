package com.example.splash_ui.confirm;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splash_ui.R;

import java.util.ArrayList;

public class ConfirmOrderActivityAdapter extends RecyclerView.Adapter<ConfirmOrderActivityAdapter.ConfirmOrderViewHolder> {

    private ArrayList<OrderListModel> orderListModels;
 Activity activity;
    private UpdateSelectedItems updateSelectedItems;
    ArrayList<OrderListModel> selectedItems;


    public ConfirmOrderActivityAdapter(Activity activity,ArrayList<OrderListModel> selectedItems) {
        this.activity=activity;
        this.updateSelectedItems = updateSelectedItems;
        if (updateSelectedItems!= null) {
            this.orderListModels = updateSelectedItems.getItems();
        } else {
            this.orderListModels = new ArrayList<>();
        }
        this.selectedItems = selectedItems;

    }

    @NonNull
    @Override
    public ConfirmOrderActivityAdapter.ConfirmOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item,parent,false);
       ConfirmOrderViewHolder confirmOrderViewHolder=new ConfirmOrderViewHolder(v);
        return confirmOrderViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ConfirmOrderActivityAdapter.ConfirmOrderViewHolder holder, int position) {
          OrderListModel currentItem=orderListModels.get(position);
          holder.iv1.setImageResource(currentItem.getImage());
          holder.tv1.setText(currentItem.getName());
          holder.tv2.setText(String.valueOf(currentItem.getPrice()));
    }

    @Override
    public int getItemCount() {
        return orderListModels.size();
    }

    public class ConfirmOrderViewHolder extends RecyclerView.ViewHolder {
        ImageView iv1;
        TextView tv1,tv2;
        public ConfirmOrderViewHolder(@NonNull View itemView) {
            super(itemView);
             iv1=itemView.findViewById(R.id.food_image);
             tv1=itemView.findViewById((R.id.food_title));
             tv2=itemView.findViewById(R.id.food_price);

        }
    }
}
