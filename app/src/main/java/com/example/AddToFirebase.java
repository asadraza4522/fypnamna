package com.example;

import java.io.Serializable;

public class AddToFirebase implements Serializable {
    int packageimage;
    private String packageName;
    private String companyName;
    private String packageType;
    private String packageCharges;
    private String securityType;
    private String weaponType;
    private String dutyhours;
    private String guards;
    private String numOfGuards;
    private String startTime;
    private String endTime;
    private String packageDays;
    private String packageDuration;
    private String extraHoursRate;
    private String overNightRate;
    private String outCityRate;
    private String description;


    public AddToFirebase(){

    }
    public AddToFirebase(String companyName,String packageName,String packageType,String packageCharges,String packageDuration,String guardCat,String numOfGuards,
                         String weaponType,String dutyHours,String startTime,String endTime,String overNightRate,String extraHoursRate,String outCityRate,String description) {


        this.companyName=companyName;
        this.packageName=packageName;
        this.packageType=packageType;
        this.packageCharges=packageCharges;
        this.packageDuration=packageDuration;
        this.guards=guardCat;
        this.numOfGuards=numOfGuards;
        this.weaponType=weaponType;
        this.dutyhours=dutyHours;
        this.startTime=startTime;
        this.endTime=endTime;
        this.overNightRate=overNightRate;
        this.extraHoursRate=extraHoursRate;
        this.outCityRate=outCityRate;
        this.description=description;


    }

    public int getPackageimage() {
        return packageimage;
    }

    public void setPackageimage(int packageimage) {
        this.packageimage = packageimage;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }


    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPackageCharges() {
        return packageCharges;
    }

    public void setPackageCharges(String packageCharges) {
        this.packageCharges = packageCharges;
    }



    public String getSecurityType() {
        return guards;
    }

    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    public String getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }

    public String  getDutyhours() {
        return dutyhours;
    }

    public void setDutyhours(String hours) {
        this.dutyhours = hours;
    }

    public String getGuards() {
        return guards;
    }

    public void setGuards(String guards) {
        this.guards = guards;
    }

    public void setNumOfGuards(String numOfGuards){
        this.numOfGuards=numOfGuards;
    }

public  String getNumOfGuards( ){
        return numOfGuards;
}
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPackageDays() {
        return packageDays;
    }

    public void setPackageDays(String packageDays) {
        this.packageDays = packageDays;
    }

    public String getPackageDuration() {
        return packageDuration;
    }

    public void setPackageDuration(String packageDuration) {
        this.packageDuration = packageDuration;
    }

    public String getExtraHoursRate() {
        return extraHoursRate;
    }

    public void setExtraHoursRate(String extraHoursRate) {
        this.extraHoursRate = extraHoursRate;
    }

    public String getOverNightRate() {
        return overNightRate;
    }

    public void setOverNightRate(String overNightRate) {
        this.overNightRate = overNightRate;
    }

    public String getOutCityRate() {
        return outCityRate;
    }

    public void setOutCityRate(String outCityRate) {
        this.outCityRate = outCityRate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    @Override
    public String toString() {
        return "Post{" +
                "idCompany='" + companyName + '\'' +
                ", packageName='" + packageName + '\'' +
                ", packagePrice=" + packageCharges +
                ", securityType='" + securityType + '\'' +
                ", overNightRate=" + overNightRate +
                ", extraHoursRate=" + extraHoursRate +
                ", perHourRate=" + extraHoursRate +
                ", startDate='" + startTime + '\'' +
                ", expireDate='" + endTime + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}

