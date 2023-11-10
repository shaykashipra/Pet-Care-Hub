package com.example.splash_ui.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splash_ui.ModelClass.Appointment_User;
import com.example.splash_ui.ModelClass.Treatment_User;
import com.example.splash_ui.R;

import java.util.List;

public class AppointmentsAllUserAdapter extends RecyclerView.Adapter<AppointmentsAllUserAdapter.MyViewHolder> {
    private List<Appointment_User> mList;
    private Context context;

    public AppointmentsAllUserAdapter(List<Appointment_User> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }


    @NonNull
    @Override
    public AppointmentsAllUserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.appointments_all_user_model,parent,false);
        return new AppointmentsAllUserAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentsAllUserAdapter.MyViewHolder holder, int position) {
        holder.setData(mList.get(position).get_doctorid(),mList.get(position).getDoctor(),mList.get(position).getUsername(),mList.get(position).getDate(),mList.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder{

        private TextView doctorid,doctorname,username,date,time;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            doctorid = itemView.findViewById(R.id.doctorid);
            username = itemView.findViewById(R.id.username);
            doctorname = itemView.findViewById(R.id.doctorname);
            date = itemView.findViewById(R.id.appointment_date);
            time = itemView.findViewById(R.id.Time);


        }

        private void setData(String _doctorid,String doctorname,String username,String date,String time){
            this.doctorid.setText(_doctorid+"");
            this.doctorname.setText(doctorname+"");

            this.username.setText(""+username+"");
            this.date.setText(date+"");
            this.time.setText(time+"");
        }
    }
}
