package com.example.maste.rozkladkpi2.Retrofit;

import com.example.maste.rozkladkpi2.App;
import com.example.maste.rozkladkpi2.Retrofit.Model.AllLessonsModel.Datum;
import com.example.maste.rozkladkpi2.Retrofit.Model.WeekList.Lesson;
import com.example.maste.rozkladkpi2.Retrofit.Model.WeekNumberModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Controller {

    public Controller() {
    }


    //Сортировка по неделям (1,2)
    public ArrayList<Lesson> sortedByWeekSubject(ArrayList<Lesson> inputList, int weekNum){
        ArrayList<Lesson> sortedWeekLessons = new ArrayList<>();
        for (Lesson date: inputList){
            if (date.getLessonWeekNum() == weekNum){
                sortedWeekLessons.add(date);
            }
        }

        return sortedWeekLessons;
    }


    //Сортировка по дням
    public ArrayList<ArrayList<Lesson>> sortedByDay(ArrayList<Lesson> inputList) {
        ArrayList<ArrayList<Lesson>> outputList = new ArrayList<ArrayList<Lesson>>();
        int days = 6;
        for (int i = 1; i <= days; i++) {
            ArrayList<Lesson> lessons = new ArrayList<>();
            for (Lesson lesson: inputList){
                if (lesson.getLessonDayNum() == i){
                    lessons.add(lesson);
                    if (inputList.get(i+1).getLessonDayNum() != Integer.parseInt(inputList.get(i).getLessonNum())){
                        outputList.add(i-1, lessons);
                    }
                }
                /*
                if (i + 1 == inputList.size()) {
                    outputList.add(i - 1, lessons);
                }
                */
            }
        }
        return outputList;
    }
}

