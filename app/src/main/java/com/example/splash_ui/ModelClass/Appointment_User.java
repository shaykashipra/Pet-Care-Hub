package com.example.splash_ui.ModelClass;

public class Appointment_User {

    private String doctor,time,date,_doctorid,username;


    public Appointment_User(String doctor, String time, String date, String doctorid,String username) {
        this.doctor = doctor;
        this.time = time;
        this.date = date;
        this._doctorid = doctorid;
        this.username=username;

    }

    public String getUsername() {
        return username;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String get_doctorid() {
        return _doctorid;
    }
}
