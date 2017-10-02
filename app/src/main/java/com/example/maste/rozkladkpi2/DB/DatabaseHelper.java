package com.example.maste.rozkladkpi2.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.maste.rozkladkpi2.Retrofit.Model.AllLessonsModel.Datum;
import com.example.maste.rozkladkpi2.Retrofit.Model.WeekList.Lesson;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "LessonTable.db";
    private static final String TABLE_NAME_LESSONS = "lesson_table";
    private static final String TABLE_NAME_GROUP = "group_table";

    private static final String COL_GROUP_ID = "ID";
    private static final String COL_GROUP_NAME ="GROUP_NAME";

    private static final String COL_ID = "ID";
    private static final String COL_LESSON_DAY = "LESSON_DAY";
    private static final String COL_LESSON_DAY_NUM = "LESSON_DAY_NUM";
    private static final String COL_LESSON_NAME = "LESSON_NAME";
    private static final String COL_LESSON_ROOM = "LESSON_ROOM";
    private static final String COL_LESSON_TYPE = "LESSON_TYPE";
    private static final String COL_LESSON_TEACHER_NAME = "LESSON_TEACHER_NAME";
    private static final String COL_LESSON_NUM = "LESSON_NUM";
    private static final String COL_LESSON_START_DATE = "LESSON_START_DATE";
    private static final String COL_LESSON_END_DATE = "LESSON_END_DATE";
    private static final String COL_LESSON_WEEK_NUM = "LESSON_WEEK_NUM";
    private static final String COL_LESSON_GROUP_NAME = "LESSON_GROUP_NAME";

    private long result;

    private String[] dayArray = {"Понеділок","Вівторок","Середа","Четвер","П’ятниця","Субота"};

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 2);
        SQLiteDatabase db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME_LESSONS + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_LESSON_DAY + " TEXT, " + COL_LESSON_DAY_NUM + " INTEGER, " + COL_LESSON_NAME + " TEXT, " + COL_LESSON_ROOM + " TEXT, "
                + COL_LESSON_TYPE + " TEXT, " + COL_LESSON_TEACHER_NAME + " TEXT, "
                + COL_LESSON_NUM + " TEXT, " + COL_LESSON_START_DATE + " TEXT, "
                + COL_LESSON_END_DATE + " TEXT, " + COL_LESSON_WEEK_NUM + " INTEGER, "
                + COL_LESSON_GROUP_NAME + " TEXT);");
        db.execSQL("create table " + TABLE_NAME_GROUP + " (" + COL_GROUP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_GROUP_NAME + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_LESSONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_GROUP);
        onCreate(db);
    }


    public boolean insertGroup(String groupName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_GROUP_NAME, groupName);
        result = db.insertWithOnConflict(TABLE_NAME_GROUP, null, cv, SQLiteDatabase.CONFLICT_IGNORE);
        if (result == -1) return false;
        else return true;
    }
    public boolean insertDataList(ArrayList<Lesson> inputList, String groupName){
        SQLiteDatabase db = this.getWritableDatabase();

        for (Lesson item: inputList){
            ContentValues cv = new ContentValues();
            cv.put(COL_LESSON_DAY, item.getLessonDay());
            cv.put(COL_LESSON_DAY_NUM, item.getLessonNum());
            cv.put(COL_LESSON_NAME, item.getLessonName());
            cv.put(COL_LESSON_ROOM, item.getLessonRoom());
            cv.put(COL_LESSON_TYPE, item.getLessonType());
            cv.put(COL_LESSON_TEACHER_NAME, item.getLessonTeacherName());
            cv.put(COL_LESSON_NUM, item.getLessonNum());
            cv.put(COL_LESSON_START_DATE, item.getLessonStartDate());
            cv.put(COL_LESSON_END_DATE, item.getLessonEndDate());
            cv.put(COL_LESSON_WEEK_NUM, item.getLessonWeekNum());
            cv.put(COL_LESSON_GROUP_NAME, groupName);
            result = db.insertWithOnConflict(TABLE_NAME_LESSONS, null, cv, SQLiteDatabase.CONFLICT_IGNORE);
            cv.clear();
        }
        if (result == -1) return false;
        else return true;
    }

    public boolean checkGroupInBD(String groupName, DatabaseHelper db){
        ArrayList<String> groupList;
        groupList = db.getDataOfGroups();
        boolean isCorrect = false;
        for (String group: groupList){
            if (group.equals(groupName)){
                isCorrect = true;
                break;
            }
        }
        if (isCorrect) return true;
        else return false;
    }

    public ArrayList<String> getDataOfGroups(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME_GROUP, null);
        ArrayList<String> groupsList = new ArrayList<String>();
        if (res.getCount() == 0){
            System.out.println("Group_table is empty");
        } else {
            res.moveToPrevious();
            if (res.moveToFirst()){
                do{
                    groupsList.add(res.getString(1));
                } while (res.moveToNext());
            }
        }
        return groupsList;
    }
    public ArrayList<ArrayList<Lesson>> getDataOfGroupLessons(String groupName, int weekNum){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME_LESSONS + " where " + COL_LESSON_GROUP_NAME + " = ?;", new String[] {groupName});
        ArrayList<Lesson> outputList = new ArrayList<Lesson>();
        if (res.getCount() == 0){
            System.out.println("Данные из бд, такой группы нету");
        } else {
            res.moveToPrevious();
            if (res.moveToFirst()){
                do{
                    Lesson lesson = new Lesson();
                    lesson.setLessonDay(res.getString(1));
                    lesson.setLessonDayNum(res.getInt(2));
                    lesson.setLessonName(res.getString(3));
                    lesson.setLessonRoom(res.getString(4));
                    lesson.setLessonType(res.getString(5));
                    lesson.setLessonTeacherName(res.getString(6));
                    lesson.setLessonNum(res.getString(7));
                    lesson.setLessonStartDate(res.getString(8));
                    lesson.setLessonEndDate(res.getString(9));
                    lesson.setLessonWeekNum(res.getInt(10));
                    lesson.setLessonGroupName(res.getString(11));
                    outputList.add(lesson);
                }while (res.moveToNext());
            }
        }
        res.close();
        db.close();

        ArrayList<Lesson> sortedWeekLessons = new ArrayList<>();
        for (Lesson date: outputList){
            if (date.getLessonWeekNum() == weekNum){
                sortedWeekLessons.add(date);
            }
        }
        outputList = null;

        ArrayList<ArrayList<Lesson>> sortedByDay = new ArrayList<ArrayList<Lesson>>(6);
        for (int i = 0; i < 6; i++){
            ArrayList<Lesson> lessons = new ArrayList<>();
            for (Lesson lesson: sortedWeekLessons){
                if (lesson.getLessonDay().equals(dayArray[i])){
                    lessons.add(lesson);
                }
            }
            sortedByDay.add(i, lessons);
        }

        return sortedByDay;
    }

    public void dropAllGroups(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME_GROUP);
        db.close();
    }
    public void dropAllDataInDbLessons(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME_LESSONS);
        db.close();
    }


}
