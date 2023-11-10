package com.example.splash_ui.Adapters;

import android.content.Context;
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
import com.example.splash_ui.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AllUserInfoAdapter extends RecyclerView.Adapter<AllUserInfoAdapter.MyViewHolder> {
    private List<User> mList;
    private Context context;

    public AllUserInfoAdapter(List<User> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }


    @NonNull
    @Override
    public AllUserInfoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.all_user_info_card,parent,false);
        return new AllUserInfoAdapter.MyViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull AllUserInfoAdapter.MyViewHolder holder, int position) {
        holder.setData(mList.get(position).getName(),mList.get(position).getUsername(),mList.get(position).getEmail(),mList.get(position).getPhone(),mList.get(position).getPassword());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder{

        private TextView Name,Username,Email,Phone;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.Name);
            Username = itemView.findViewById(R.id.username);
            Email = itemView.findViewById(R.id.Email);
            Phone = itemView.findViewById(R.id.phone);


        }

        private void setData(String name,String username,String email,String phone,String password){
           Name.setText(name);
           Username.setText(username);
           Email.setText(email);
           Phone.setText(phone);


        }

    }}
