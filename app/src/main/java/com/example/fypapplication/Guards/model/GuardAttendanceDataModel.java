package com.example.fypapplication.Guards.model;

public class GuardAttendanceDataModel {

    private String check_in_time;
    private String check_out_time;
    private String break_time;

    public GuardAttendanceDataModel() {
    }

    public GuardAttendanceDataModel(String check_in_time, String check_out_time, String break_time) {
        this.check_in_time = check_in_time;
        this.check_out_time = check_out_time;
        this.break_time = break_time;
    }

    public String getCheck_in_time() {
        return check_in_time;
    }

    public void setCheck_in_time(String check_in_time) {
        this.check_in_time = check_in_time;
    }

    public String getCheck_out_time() {
        return check_out_time;
    }

    public void setCheck_out_time(String check_out_time) {
        this.check_out_time = check_out_time;
    }

    public String getBreak_time() {
        return break_time;
    }

    public void setBreak_time(String break_time) {
        this.break_time = break_time;
    }
}
