package com.example.fypapplication.Post.model;

import java.io.Serializable;

public class Post implements Serializable {
    private String idCompany;
    private String packageName;
    private double packagePrice;
    private String securityType;
    private double overNightRate;
    private double extraHoursRate;
    private double perHourRate;
    private String startDate;
    private String expireDate;
    private String description;
    public long timestamp;

    public Post() {

    }

    public Post(String idCompany, String packageName, double packagePrice, String securityType, double overNightRate, double extraHoursRate, double perHourRate, String startDate, String expireDate, String description, long timestamp) {
        this.idCompany = idCompany;
        this.packageName = packageName;
        this.packagePrice = packagePrice;
        this.securityType = securityType;
        this.overNightRate = overNightRate;
        this.extraHoursRate = extraHoursRate;
        this.perHourRate = perHourRate;
        this.startDate = startDate;
        this.expireDate = expireDate;
        this.description = description;
        this.timestamp = timestamp;
    }

    public String getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(String idCompany) {
        this.idCompany = idCompany;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public double getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(double packagePrice) {
        this.packagePrice = packagePrice;
    }

    public String getSecurityType() {
        return securityType;
    }

    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    public double getOverNightRate() {
        return overNightRate;
    }

    public void setOverNightRate(double overNightRate) {
        this.overNightRate = overNightRate;
    }

    public double getExtraHoursRate() {
        return extraHoursRate;
    }

    public void setExtraHoursRate(double extraHoursRate) {
        this.extraHoursRate = extraHoursRate;
    }

    public double getPerHourRate() {
        return perHourRate;
    }

    public void setPerHourRate(double perHourRate) {
        this.perHourRate = perHourRate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Post{" +
                "idCompany='" + idCompany + '\'' +
                ", packageName='" + packageName + '\'' +
                ", packagePrice=" + packagePrice +
                ", securityType='" + securityType + '\'' +
                ", overNightRate=" + overNightRate +
                ", extraHoursRate=" + extraHoursRate +
                ", perHourRate=" + perHourRate +
                ", startDate='" + startDate + '\'' +
                ", expireDate='" + expireDate + '\'' +
                ", description='" + description + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

}
