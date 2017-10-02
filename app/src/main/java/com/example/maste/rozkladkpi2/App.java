package com.example.maste.rozkladkpi2;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.maste.rozkladkpi2.Retrofit.RozkladApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CurrentTime currentTime = new CurrentTime();
        setPref("selected_group", String.valueOf(currentTime.getCurrentWeekDay()));
    }

    void setPref(String key, String groupName ){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (groupName != null){
            editor.putString(key, groupName);
        } else {
            editor.putString(key, groupName);
        }


        editor.apply();
    }

}