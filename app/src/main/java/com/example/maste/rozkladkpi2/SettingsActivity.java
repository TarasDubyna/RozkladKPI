package com.example.maste.rozkladkpi2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.example.maste.rozkladkpi2.DB.DatabaseHelper;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button deleteGroupsBtn;

    private ListView groupsListView;
    private TextView groupsTextView;

    SharedPreferences sPref;
    DatabaseHelper dbHelper;
    ArrayAdapter<String> groupsAdapter;

    boolean delated = false;

    private final static String GROUP_NAME = "group_name";
    private final static String WEEK_NUM = "week_num";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        deleteGroupsBtn = (Button) findViewById(R.id.settings_delete_groups_button);
        groupsListView = (ListView) findViewById(R.id.settings_groups_listview);
        groupsTextView = (TextView) findViewById(R.id.settings_groups_text);

        deleteGroupsBtn.setOnClickListener(this);

        dbHelper = new DatabaseHelper(this);
        if (!dbHelper.getDataOfGroups().isEmpty()){
            groupsTextView.setText(getResources().getText(R.string.settings_groups_text));
            groupsAdapter = new ArrayAdapter<String>(this, R.layout.list_group_item,dbHelper.getDataOfGroups());
            groupsListView.setAdapter(groupsAdapter);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.settings_delete_groups_button:
                dbHelper.dropAllDataInDbLessons();
                dbHelper.dropAllGroups();
                setInPref(GROUP_NAME,"");
                setInPref(WEEK_NUM,"");
                groupsAdapter.clear();
                groupsAdapter.notifyDataSetChanged();
                delated = true;
                break;
        }
    }
    @Override
    public void onBackPressed() {
        if (delated){
            Intent intent = new Intent(SettingsActivity.this, EnterActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    void setInPref(String key, String groupName ){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, groupName);
        editor.apply();
    }
}
