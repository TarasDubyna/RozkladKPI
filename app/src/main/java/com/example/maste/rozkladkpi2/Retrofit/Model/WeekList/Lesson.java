package com.example.maste.rozkladkpi2.Retrofit.Model.WeekList;

import com.google.gson.annotations.SerializedName;

public class Lesson {

    @SerializedName("day_name")
    private String lessonDay;

    @SerializedName("day_number")
    private int lessonDayNum;

    @SerializedName("lesson_name")
    private String lessonName;

    @SerializedName("lesson_room")
    private String lessonRoom;

    @SerializedName("lesson_type")
    private String lessonType;

    @SerializedName("teacher_name")
    private String lessonTeacherName;

    @SerializedName("lesson_number")
    private String lessonNum;

    @SerializedName("time_start")
    private String lessonStartDate;

    @SerializedName("time_end")
    private String lessonEndDate;

    @SerializedName("lesson_week")
    private int lessonWeekNum;

    //@SerializedName("lesson_id")
    private String lessonGroupName;

    public Lesson() {
    }


    public String getLessonDay() {
        return lessonDay;
    }

    public void setLessonDay(String lessonDay) {
        this.lessonDay = lessonDay;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLessonRoom() {
        return lessonRoom;
    }

    public void setLessonRoom(String lessonRoom) {
        this.lessonRoom = lessonRoom;
    }

    public String getLessonType() {
        return lessonType;
    }

    public void setLessonType(String lessonType) {
        this.lessonType = lessonType;
    }

    public String getLessonTeacherName() {
        return lessonTeacherName;
    }

    public void setLessonTeacherName(String lessonTeacherName) {
        this.lessonTeacherName = lessonTeacherName;
    }


    public String getLessonStartDate() {
        return lessonStartDate;
    }

    public void setLessonStartDate(String lessonStartDate) {
        this.lessonStartDate = lessonStartDate;
    }

    public String getLessonEndDate() {
        return lessonEndDate;
    }

    public void setLessonEndDate(String lessonEndDate) {
        this.lessonEndDate = lessonEndDate;
    }


    public String getLessonGroupName() {
        return lessonGroupName;
    }

    public void setLessonGroupName(String lessonGroupName) {
        this.lessonGroupName = lessonGroupName;
    }

    public int getLessonDayNum() {
        return lessonDayNum;
    }

    public void setLessonDayNum(int lessonDayNum) {
        this.lessonDayNum = lessonDayNum;
    }

    public String getLessonNum() {
        return lessonNum;
    }

    public void setLessonNum(String lessonNum) {
        this.lessonNum = lessonNum;
    }

    public int getLessonWeekNum() {
        return lessonWeekNum;
    }

    public void setLessonWeekNum(int lessonWeekNum) {
        this.lessonWeekNum = lessonWeekNum;
    }
}

