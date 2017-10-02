package com.example.maste.rozkladkpi2.Retrofit.Model.WeekList;

public class Day {

    private String dayName;
    private Lesson lesson;

    public Day() {
    }

    public Day(String dayName, Lesson lesson) {
        this.dayName = dayName;
        this.lesson = lesson;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}
