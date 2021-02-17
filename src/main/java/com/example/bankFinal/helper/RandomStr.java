package com.example.bankFinal.helper;

import java.util.Date;
import java.util.Random;

public class RandomStr {
    public static String randomString() {
        String res = "";
        Date date = new Date();
        String seconds = String.valueOf(date.getSeconds());
        if (seconds.length() == 1) {
            seconds = "0" + seconds;
        }
        String year = String.valueOf(date.getYear());
        String mont = String.valueOf(date.getMonth());
        if (mont.length() == 1) {
            mont = "0" + mont;
        }
        String day = String.valueOf(date.getDay());
        if (day.length() == 1) {
            day = "0" + day;
        }
        String minutes = String.valueOf(date.getMinutes());
        if (minutes.length() == 1) {
            minutes = "0" + minutes;
        }
        String hours = String.valueOf(date.getHours());
        if (hours.length() == 1) {
            hours = "0" + hours;
        }
        String random =  String.valueOf((int) (getRandomNumber(1000000000, 2000000000)));
        res = year + mont + day + minutes + hours + seconds + random;
        System.out.println(res.length());
        return res ;
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
