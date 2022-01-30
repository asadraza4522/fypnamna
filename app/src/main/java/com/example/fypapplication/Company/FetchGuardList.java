package com.example.fypapplication.Company;

import java.io.Serializable;

public class FetchGuardList implements Serializable {

    String email,name;

    public FetchGuardList(){

    }
    public FetchGuardList(String name,String email){
this.name=name;
this.email=email;

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
