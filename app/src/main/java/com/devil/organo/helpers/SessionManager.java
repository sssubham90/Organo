package com.devil.organo.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

public class SessionManager {
    private SharedPreferences pref;
    private Editor editor;
    private Context _context;
    private static final String PREF_NAME = "AndroidHivePref";
    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }
     
    public void createLoginSession(String email){
        editor = pref.edit();
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_EMAIL, email);
        editor.apply();
    }

    HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<>();
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        return user;
    }

    public String getEmail(){
        return pref.getString(KEY_EMAIL,null);
    }

}