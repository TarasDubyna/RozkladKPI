package com.example.maste.rozkladkpi2;

import android.provider.Settings;

import java.util.Calendar;
import java.util.Date;

import javax.sound.midi.SysexMessage;

public class CurrentTime {

    public CurrentTime() {
    }

    public String getCurrentTime(){

        Date currentDate = new Date();
        java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("HH:mm:ss");
        return simpleDateFormat.format(currentDate);

    }

    public int getCurrentWeekDay(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_WEEK) - 2;
    }
}
