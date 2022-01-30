package com.example.fypapplication.Post.utils;

import java.sql.Timestamp;
import java.util.Date;

public class Utils {
    public static long getCurrentTime() {
        //Date object
        Date date = new Date();
        //getTime() returns current time in milliseconds
        return date.getTime();
    }
}
