package com.example.fypapplication.Post.utils;

import android.content.SharedPreferences;

import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class Utils {

    public static long getCurrentTime() {
        //Date object
        Date date = new Date();
        //getTime() returns current time in milliseconds
        return date.getTime();
    }

}
