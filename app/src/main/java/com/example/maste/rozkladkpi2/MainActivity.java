package com.example.maste.rozkladkpi2;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.maste.rozkladkpi2.DB.DatabaseHelper;
import com.example.maste.rozkladkpi2.Retrofit.Controller;
import com.example.maste.rozkladkpi2.Retrofit.Model.WeekList.Lesson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static int numWeek = 1;
    private static String group_name = null;
    private int lastExpandedPosition = -1;

    ExpandableListView weekListView;
    Button firstWeekBtn;
    Button secondWeekBtn;
    Button settingsBtn;
    Button exitBtn;

    SharedPreferences sPref;
    DatabaseHelper dbHelper;

    private final static String GROUP_NAME = "group_name";
    private final static String WEEK_NUM = "week_num";
    private final static String SELECTED_GROUP = "selected_group";

    ArrayList<ArrayList<Lesson>> fromDbList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstWeekBtn = (Button) findViewById(R.id.main_first_week_btn);
        secondWeekBtn = (Button) findViewById(R.id.main_second_week_btn);
        exitBtn = (Button) findViewById(R.id.main_change_button);
        settingsBtn = (Button) findViewById(R.id.main_settings_button);
        weekListView = (ExpandableListView) findViewById(R.id.weeks_list_view);

        firstWeekBtn.setOnClickListener(this);
        secondWeekBtn.setOnClickListener(this);
        exitBtn.setOnClickListener(this);
        settingsBtn.setOnClickListener(this);

        fromDbList = new ArrayList<ArrayList<Lesson>>();
        dbHelper = new DatabaseHelper(this);

        if (!getPref(WEEK_NUM).isEmpty()){
            if (getPref(WEEK_NUM).equals("1")){
                firstWeekBtn.setBackgroundColor(getResources().getColor(R.color.blue_4));
                secondWeekBtn.setBackgroundColor(getResources().getColor(R.color.white));
                fromDbList = dbHelper.getDataOfGroupLessons(getPref(GROUP_NAME), 1);
                doWork(fromDbList, MainActivity.this);
            }
            if (getPref(WEEK_NUM).equals("2")){
                firstWeekBtn.setBackgroundColor(getResources().getColor(R.color.white));
                secondWeekBtn.setBackgroundColor(getResources().getColor(R.color.blue_4));
                fromDbList = dbHelper.getDataOfGroupLessons(getPref(GROUP_NAME), 2);
                doWork(fromDbList, MainActivity.this);
            }
        } else {
            firstWeekBtn.setBackgroundColor(getResources().getColor(R.color.blue_4));
            secondWeekBtn.setBackgroundColor(getResources().getColor(R.color.white));
            fromDbList = dbHelper.getDataOfGroupLessons(getPref(GROUP_NAME), 1);
            doWork(fromDbList, MainActivity.this);
        }
    }


    @Override
    public void onClick(View v) {
        Intent intent = getIntent();
        switch (v.getId()) {
            case R.id.main_first_week_btn:
                firstWeekBtn.setBackgroundColor(getResources().getColor(R.color.blue_4));
                secondWeekBtn.setBackgroundColor(getResources().getColor(R.color.white));
                setPref(WEEK_NUM, "1");
                fromDbList = dbHelper.getDataOfGroupLessons(getPref(GROUP_NAME), 1);
                doWork(fromDbList, MainActivity.this);
                break;
            case R.id.main_second_week_btn:
                firstWeekBtn.setBackgroundColor(getResources().getColor(R.color.white));
                secondWeekBtn.setBackgroundColor(getResources().getColor(R.color.blue_4));
                setPref(WEEK_NUM, "2");
                fromDbList = dbHelper.getDataOfGroupLessons(getPref(GROUP_NAME), 2);
                doWork(fromDbList, MainActivity.this);
                break;



            case R.id.main_change_button:
                setPref(GROUP_NAME, "");
                Intent intentChange = new Intent(MainActivity.this, EnterActivity.class);
                System.out.println("Переход на другое активити");
                startActivity(intentChange);
                break;
            case R.id.main_settings_button:
                Intent intentSettings = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intentSettings);
                break;
        }
    }


    private void doWork(ArrayList<ArrayList<Lesson>>  data, Context context){
        if (data != null || data.size() == 0){
            ListAdapter la = new ListAdapter(getApplicationContext(),data);
            weekListView.setAdapter(la);
            weekListView.expandGroup(Integer.parseInt(getPref(SELECTED_GROUP)), true);
            weekListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                @Override
                public void onGroupExpand(int groupPosition) {
                    if (Integer.parseInt(getPref(SELECTED_GROUP)) != -1 && groupPosition != Integer.parseInt(getPref(SELECTED_GROUP))) {
                        weekListView.collapseGroup(Integer.parseInt(getPref(SELECTED_GROUP)));
                    }
                    setPref(SELECTED_GROUP, String.valueOf(groupPosition));
                }
            });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //weekListView.expandGroup(Integer.parseInt(getPref(SELECTED_GROUP)));
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

    String getPref(String key){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        return sharedPreferences.getString(key,"");
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        setPref(SELECTED_GROUP,"");
    }
}
