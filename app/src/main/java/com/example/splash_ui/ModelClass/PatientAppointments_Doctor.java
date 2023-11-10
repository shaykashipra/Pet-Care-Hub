package com.example.splash_ui.ModelClass;

public class PatientAppointments_Doctor {

    private String date,doctor,patient,time;

    public PatientAppointments_Doctor(String date, String doctor, String patient, String time) {
        this.date = date;
        this.doctor = doctor;
        this.patient = patient;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getPatient() {
        return patient;
    }

    public String getTime() {
        return time;
    }
}
