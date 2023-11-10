package com.example.splash_ui.Clinic;

public class User2 {
    private String doctor,time,date,_doctorid;
    User2()
    {

    }

    public User2(String doctor, String time, String date, String _doctorid) {
        this.doctor = doctor;
        this.time = time;
        this.date = date;
        this._doctorid = _doctorid;
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
