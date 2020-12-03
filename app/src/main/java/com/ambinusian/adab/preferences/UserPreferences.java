package com.ambinusian.adab.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.core.content.res.ResourcesCompat;

import com.ambinusian.adab.R;

import java.lang.reflect.Type;

public class UserPreferences {

    private static final String KEY_USER_USERNAME = "user_username";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_TOKEN = "user_token";
    private static final String KEY_USER_LOGGED_IN = "user_logged_in";
    private static final String KEY_USER_PRIVILEGE = "user_privilege";
    private static final String KEY_USER_DEPARTMENT = "user_departement"; //TODO: Bang, ini "department"
    private static final String KEY_PREF_NIGHT = "pref_night";
    private static final String KEY_TEXT_SIZE = "text_size";
    private static final String KEY_TEXT_FAMILY = "text_family";
    private static final String KEY_TEXT_STYLE = "text_style";

    private static Context context = null;

    public UserPreferences(Context context) {
        this.context = context;
    }

    private static SharedPreferences getSharedPreference() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setUserUsername(String userid){
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.putString(KEY_USER_USERNAME, userid);
        editor.apply();
    }

    public String getUserUsername(){
        return getSharedPreference().getString(KEY_USER_USERNAME,"");
    }

    public void setUserName(String userName){
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.putString(KEY_USER_NAME, userName);
        editor.apply();
    }

    public String getUserName() { return getSharedPreference().getString(KEY_USER_NAME,""); }

    public void setUserToken(String usertoken){
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.putString(KEY_USER_TOKEN, usertoken);
        editor.apply();
    }

    public String getUserToken(){
        return getSharedPreference().getString(KEY_USER_TOKEN,"");
    }

    public void setUserLoggedIn(Boolean loggedin){
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.putBoolean(KEY_USER_LOGGED_IN, loggedin);
        editor.apply();
    }

    public Boolean getUserLoggedIn(){
        return getSharedPreference().getBoolean(KEY_USER_LOGGED_IN,false);
    }

    public void setUserPrivilege(int userprivilege){
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.putInt(KEY_USER_PRIVILEGE, userprivilege);
        editor.apply();
    }

    public int getUserPrivilege() {
        return getSharedPreference().getInt(KEY_USER_PRIVILEGE,0);
    }

    public void setUserDepartement(String userdepartement){
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.putString(KEY_USER_DEPARTMENT, userdepartement);
        editor.apply();
    }

    public String getUserDepartement() {
        return getSharedPreference().getString(KEY_USER_DEPARTMENT,"");
    }

    public void clearLoggedInUser() {
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.remove(KEY_USER_USERNAME);
        editor.remove(KEY_USER_NAME);
        editor.remove(KEY_USER_TOKEN);
        editor.remove(KEY_USER_LOGGED_IN);
        editor.remove(KEY_USER_PRIVILEGE);
        editor.remove(KEY_USER_DEPARTMENT);
        editor.apply();
    }

    public void setPrefNight(int mode){
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.putInt(KEY_PREF_NIGHT, mode);
        editor.apply();
    }

    public int getPrefNight() {
        return getSharedPreference().getInt(KEY_PREF_NIGHT,0);
    }

    public void setTextSize(float textSize){
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.putFloat(KEY_TEXT_SIZE, textSize);
        editor.apply();
    }

    public float getTextSize(){
        return getSharedPreference().getFloat(KEY_TEXT_SIZE,1f);
    }

    public void setTextTypeface(int typefaceFamily){
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.putInt(KEY_TEXT_FAMILY, typefaceFamily);
        editor.apply();
    }

    public Typeface getTextTypeface(){
        int typefaceFamily = getSharedPreference().getInt(KEY_TEXT_FAMILY,0);

        Typeface typeface;

//        pilih font family
        switch (typefaceFamily) {
            case 1:
                typeface = ResourcesCompat.getFont(context, R.font.opendyslexic);
                break;
            default:
                typeface = ResourcesCompat.getFont(context, R.font.interstate);
                break;
        }

        return typeface;
    }

    public Typeface getTextTypeface(int typefaceFamily){

        Typeface typeface;

//        pilih font family
        switch (typefaceFamily) {
            case 1:
                typeface = ResourcesCompat.getFont(context, R.font.opendyslexic);
                break;
            default:
                typeface = ResourcesCompat.getFont(context, R.font.interstate);
                break;
        }

        return typeface;
    }

    public int getTextTypefaceFamilyID() {
        return getSharedPreference().getInt(KEY_TEXT_FAMILY, 0);
    }
}