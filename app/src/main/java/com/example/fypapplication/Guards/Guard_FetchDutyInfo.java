package com.example.fypapplication.Guards;

public class Guard_FetchDutyInfo {
    public String location, time;


    public Guard_FetchDutyInfo(String location, String time) {
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
