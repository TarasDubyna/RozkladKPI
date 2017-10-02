package com.example.maste.rozkladkpi2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maste.rozkladkpi2.DB.DatabaseHelper;
import com.example.maste.rozkladkpi2.Retrofit.Model.WeekList.Lesson;
import com.google.gson.Gson;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class EnterActivity extends AppCompatActivity {

    private String saved_group_num;
    private String group_name;
    private EditText enterGroupEditText;
    private Button enterGroupButton;
    private DatabaseHelper databaseHelper;
    SharedPreferences sPref;

    private final static String GROUP_NAME = "group_name";
    private final static String WEEK_NUM = "week_num";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String saved_group_num = sharedPreferences.getString(GROUP_NAME,"");

        enterGroupButton = (Button) findViewById(R.id.enter_button);

        if (!saved_group_num.isEmpty()){
            goNextActivity(saved_group_num);
        }

        enterGroupEditText = (EditText) findViewById(R.id.enter_edit);
        enterGroupButton = (Button) findViewById(R.id.enter_button);
        enterGroupEditText = (EditText) findViewById(R.id.enter_edit);

        databaseHelper = new DatabaseHelper(this);
        enterGroupButton.setClickable(true);
        enterGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterGroupButton.setClickable(false);
                group_name = enterGroupEditText.getText().toString().toLowerCase();
                if (!group_name.isEmpty()){
                    if (!databaseHelper.checkGroupInBD(group_name, databaseHelper)){
                        new GetLessonsTask().execute(group_name);
                    }else {
                        Toast.makeText(EnterActivity.this, "Data get from BD", Toast.LENGTH_SHORT).show();
                        goNextActivity(group_name);
                    }
                } else {
                    Toast.makeText(EnterActivity.this, "Поле ввода - пустое!", Toast.LENGTH_SHORT).show();
                    enterGroupEditText.setText("");
                    enterGroupButton.setClickable(true);
                }

            }
        });
    }

    private final static String ERR_INTERNET_CONN = "ERR_INTERNET_CONN";
    private final static String ERR_WRONG_GROUP = "ERR_WRONG_GROUP";

    public class GetLessonsTask extends AsyncTask<String, String, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            String finalJson = null;
            try{
                URL url = new URL("https://api.rozklad.org.ua/v2/groups/" + group_name + "/lessons");
                connection = (HttpURLConnection) url.openConnection();
                finalJson = ERR_INTERNET_CONN;
                connection.connect();

                InputStream stream = null;
                finalJson = ERR_WRONG_GROUP;
                stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null){
                    buffer.append(line);
                }
                finalJson = buffer.toString();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null){
                    connection.disconnect();
                }
                try {
                    if (reader != null){
                        reader.close();
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            return finalJson;
        }

        @Override
        protected void onPostExecute(String data) {
            super.onPostExecute(data);

            if (data.equals(ERR_INTERNET_CONN)){
                //error with internet connection
                Toast.makeText(EnterActivity.this, "Ошибка при подключении к интернету, проверьте подключение!", Toast.LENGTH_SHORT ).show();
                enterGroupEditText.setText("");
                enterGroupButton.setClickable(true);
                group_name = null;
            } else if (data.equals(ERR_WRONG_GROUP)){
                //wrong group name
                Toast.makeText(EnterActivity.this, "Группы с таким именем не существует", Toast.LENGTH_SHORT ).show();
                enterGroupEditText.setText("");
                enterGroupButton.setClickable(true);
                group_name = null;
            } else if (!data.isEmpty()){
                try {
                    JSONObject parentObject = null;
                    parentObject = new JSONObject(data);
                    JSONArray parentArray = parentObject.getJSONArray("data");
                    ArrayList<Lesson> lessonModelList = new ArrayList<>();
                    Gson gson = new Gson();
                    for (int i = 0; i < parentArray.length(); i++){
                        JSONObject finalObject = parentArray.getJSONObject(i);
                        Lesson lesson = gson.fromJson(finalObject.toString(), Lesson.class);
                        lesson.setLessonGroupName(group_name);
                        lessonModelList.add(lesson);
                    }

                    databaseHelper = new DatabaseHelper(EnterActivity.this);
                    databaseHelper.insertDataList(lessonModelList, group_name);
                    databaseHelper.insertGroup(group_name);
                    goNextActivity(group_name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public class GetWeekTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try{
                URL url = new URL("https://api.rozklad.org.ua/v2/weeks");
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null){
                    buffer.append(line);
                }
                String finalJson = buffer.toString();
                JSONObject parentObject = new JSONObject(finalJson);
                return parentObject.getString("data");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null){
                    connection.disconnect();
                }
                try {
                    if (reader != null){
                        reader.close();
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String data) {
            super.onPostExecute(data);
            if (!data.isEmpty()){
                setInPref(WEEK_NUM,data);
            }
        }
    }


    void setInPref(String key, String groupName ){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, groupName);
        editor.apply();
    }

    void goNextActivity(String savedGroupNum){
        Intent intent = new Intent(EnterActivity.this, MainActivity.class);
        System.out.println("Переход на другое активити");
        intent.putExtra("group_name", savedGroupNum);
        if (!enterGroupButton.isClickable()){
            enterGroupButton.setClickable(true);
        }
        setInPref(GROUP_NAME,savedGroupNum);
        startActivity(intent);
    }

}
