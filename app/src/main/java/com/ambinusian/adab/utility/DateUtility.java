package com.ambinusian.adab.utility;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class DateUtility {

    public static Date convertStringToDate(String dateStr){
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String convertToDateAndTimeFormat(String dateStr){
        Date date = convertStringToDate(dateStr);
        return new SimpleDateFormat("EEEE, d MMMM YYYY, HH:mm").format(date);
    }

    public static String convertToDateFormat(String dateStr){
        Date date = convertStringToDate(dateStr);
        return new SimpleDateFormat("EEEE, d MMMM YYYY").format(date);
    }

    public static String convertToDateFormat(Date date){
        return new SimpleDateFormat("EEEE, d MMMM YYYY").format(date);
    }

    public static String convertToTimeFormat(String dateStr){
        Date date = convertStringToDate(dateStr);
        return new SimpleDateFormat("HH:mm").format(date);
    }
}
