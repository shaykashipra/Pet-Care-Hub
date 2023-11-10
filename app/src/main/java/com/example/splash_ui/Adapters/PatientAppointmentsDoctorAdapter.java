package com.example.splash_ui.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splash_ui.ModelClass.Appointment_User;
import com.example.splash_ui.ModelClass.PatientAppointments_Doctor;
import com.example.splash_ui.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class PatientAppointmentsDoctorAdapter extends RecyclerView.Adapter<PatientAppointmentsDoctorAdapter.MyViewHolder> {
    private List<PatientAppointments_Doctor> mList;


    private Context context;
    private PatientAppointmentsDoctorAdapter adapter;

    public PatientAppointmentsDoctorAdapter(List<PatientAppointments_Doctor> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }


    @NonNull
    @Override
    public PatientAppointmentsDoctorAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.patient_appointments_model, parent, false);
        return new PatientAppointmentsDoctorAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientAppointmentsDoctorAdapter.MyViewHolder holder, int position) {
        holder.setData(mList.get(position).getDate(), mList.get(position).getDoctor(), mList.get(position).getPatient(), mList.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView date, doctor, patient, time;
        String key,doctor_user;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.appointment_date);
            doctor = itemView.findViewById(R.id.DoctorName);
            patient = itemView.findViewById(R.id.username);

            time = itemView.findViewById(R.id.Time);



            itemView.findViewById(R.id.minus).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    // Check if the position is valid
                    if (position != RecyclerView.NO_POSITION) {

                     //   PatientAppointments_Doctor deletedPatient = mList.get(position);
                        String doctorUser = getDoctorUser( mList.get(position).getDoctor());
                        String doctorID = getDoctorID( mList.get(position).getDoctor());


                        Log.d("PatientDeletion", "retrive doctor user"+ doctorID);




                        FirebaseDatabase.getInstance().getReference("doctor/" + doctorUser+ "/patients/"+mList.get(position).getPatient()).removeValue()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Log.d("PatientDeletion", "Data deleted successfully");
                                        mList.remove(position);
                                        notifyItemRemoved(position);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("PatientDeletion", "Error deleting patient data: " + e.getMessage());

                                    }
                                });


                        FirebaseDatabase.getInstance().getReference("users/" + mList.get(position).getPatient()+ "/appointment/"+doctorID).removeValue()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {


                                    }
                                });
                    }
                }
            });



        }



        private String getDoctorUser(String doctorName) {
            if (doctorName.equals("Dr. Raonok")) {
                return "@raonok123";
            } else if (doctorName.equals("Dr. Alia")) {
                return "@triva1";
            }
            return "";
        }

        private String getDoctorID(String doctorName) {
            if (doctorName.equals("Dr. Raonok")) {
                return "21419";
            } else if (doctorName.equals("Dr. Alia")) {
                return "21420";
            }
            return "";
        }

        private void setData(String Date, String doctorname, String username, String time) {
            this.date.setText(Date + "");
            this.doctor.setText(doctorname + "");

            this.patient.setText("" + username + "");

            this.time.setText(time + "");
        }
    }
}