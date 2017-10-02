package com.example.maste.rozkladkpi2.Retrofit.Model.AllLessonsModel;

import java.util.List;

public class SortedWeekLessons {
    private final List<Datum> firstWeekLessons;
    private final List<Datum> secondWeekLessons;

    public SortedWeekLessons(List<Datum> firstWeekLessons, List<Datum> secondWeekLessons) {
        this.firstWeekLessons = firstWeekLessons;
        this.secondWeekLessons = secondWeekLessons;
    }

    public List<Datum> getFirstWeekLessons() {
        return firstWeekLessons;
    }

    public List<Datum> getSecondWeekLessons() {
        return secondWeekLessons;
    }
}
