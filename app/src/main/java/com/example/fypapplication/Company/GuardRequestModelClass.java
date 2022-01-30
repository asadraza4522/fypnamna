package com.example.fypapplication.Company;

public class GuardRequestModelClass {
    String name;
    String availableno;
    String number;

    public GuardRequestModelClass(String name, String availableno, String number) {
        this.name = name;
        this.availableno=availableno;
        this.number=number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvailableno() {
        return availableno;
    }

    public void setAvailableno(String availableno) {
        this.availableno = availableno;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
