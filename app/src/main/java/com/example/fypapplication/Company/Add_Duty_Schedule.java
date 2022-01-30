package com.example.fypapplication.Company;


public class Add_Duty_Schedule {


    public String location, time;


    public Add_Duty_Schedule(String location, String time) {
        this.location = location;
        this.time = time;


    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
