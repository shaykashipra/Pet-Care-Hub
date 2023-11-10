package com.example.splash_ui.ModelClass;

import java.util.ArrayList;
import java.util.List;

public class Treatment_User {
    public String getDoctorName() {
        return DoctorName;
    }

    public String getDate() {
        return Date;
    }

    public String getAdvice() {
        return Advice;
    }


    public List<String> getMedicines() {
        return Medicines;
    }

    private String DoctorName,Date,Advice;
    List<String> Medicines=new ArrayList<>();


    public Treatment_User(String date, String doctorName, String advice, List<String> medicines) {
        DoctorName = doctorName;
        Date = date;
        Advice = advice;
        Medicines = medicines;

    }

    public Treatment_User() {
    }



}

