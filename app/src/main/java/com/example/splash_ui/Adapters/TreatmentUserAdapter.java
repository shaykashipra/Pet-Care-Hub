package com.example.splash_ui.Adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splash_ui.ModelClass.Treatment_User;
import com.example.splash_ui.R;

import java.util.List;

public class TreatmentUserAdapter extends RecyclerView.Adapter<TreatmentUserAdapter.MyViewHolder> {
    private List<Treatment_User> mList;
    private Context context;

    public TreatmentUserAdapter(List<Treatment_User> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.treatment_user_model,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setData(mList.get(position).getDate(),mList.get(position).getDoctorName(),mList.get(position).getAdvice(),mList.get(position).getMedicines());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder{

        private TextView doctorname,date,advice,medicines;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);



            doctorname = itemView.findViewById(R.id.doctorname);
            medicines = itemView.findViewById(R.id.medicine);
            advice = itemView.findViewById(R.id.advice);
            date= itemView.findViewById(R.id.ViewTreatmentsDate);



        }

        private void setData(String Date,String Doctorname,String Advice,List<String> Medicines){
            date.setText(Date);
            doctorname.setText(Doctorname);
            advice.setText(Advice);

            String medicinesText = TextUtils.join(", ", Medicines);
            medicines.setText(medicinesText);

        }
    }
}