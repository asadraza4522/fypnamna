package com.example.fypapplication.Guards.model;

public class GuardAttendanceModel {

    private String uid;
    private String duty_date;
    private GuardAttendanceDataModel guardAttendanceData;

    public GuardAttendanceModel() {
    }

    public GuardAttendanceModel(String uid, String duty_date, GuardAttendanceDataModel guardAttendanceData) {
        this.uid = uid;
        this.duty_date = duty_date;
        this.guardAttendanceData = guardAttendanceData;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDuty_date() {
        return duty_date;
    }

    public void setDuty_date(String duty_date) {
        this.duty_date = duty_date;
    }

    public GuardAttendanceDataModel getGuardAttendanceData() {
        return guardAttendanceData;
    }

    public void setGuardAttendanceData(GuardAttendanceDataModel guardAttendanceData) {
        this.guardAttendanceData = guardAttendanceData;
    }
}
