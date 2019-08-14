package com.ambinusian.adab.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class UserPreferences {

    private static final String KEY_USER_USERNAME = "user_username";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_TOKEN = "user_token";
    private static final String KEY_USER_LOGGED_IN = "user_logged_in";
    private static final String KEY_USER_PRIVILEGE = "user_privilege";
    private static final String KEY_USER_DEPARTMENT = "user_departement";

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

    public String getUserName(){
        return getSharedPreference().getString(KEY_USER_NAME,"");
    }

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
}