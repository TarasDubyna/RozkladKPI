package com.example.maste.rozkladkpi2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.maste.rozkladkpi2.Retrofit.Model.WeekList.Lesson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ListAdapter extends BaseExpandableListAdapter {

    private ArrayList<ArrayList<Lesson>> lessonsList;
    private Context mContext;
    private String[] dayArray = {"Понеділок","Вівторок","Середа","Четвер","П’ятниця","Субота"};
    private CurrentTime currentTime;

    public ListAdapter(Context cont, ArrayList<ArrayList<Lesson>> list){
        mContext = cont;
        lessonsList = list;
    }

    @Override
    public int getGroupCount() {
        return lessonsList.size();
    }
    @Override
    public int getChildrenCount(int groupPosition) {
        return lessonsList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return lessonsList.get(groupPosition);
    }
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return lessonsList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group, null);
        }

        if (isExpanded){
            //Изменяем что-нибудь, если текущая Group раскрыта
        }
        else{
            //Изменяем что-нибудь, если текущая Group скрыта
        }

        TextView textGroup = (TextView) convertView.findViewById(R.id.list_group_text);

        CurrentTime currentTime = new CurrentTime();

        if (groupPosition == currentTime.getCurrentWeekDay()){
            convertView.setBackgroundResource(R.color.blue_3);
        } else {
            convertView.setBackgroundResource(R.color.blue_4);
        }

        if (lessonsList.get(groupPosition).size() == 0){
            convertView.setBackgroundResource(R.color.colorAccent);
            textGroup.setText(dayArray[groupPosition] + " " + "(Вихідний)");
        } else {
            textGroup.setText(dayArray[groupPosition]);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);
        }

        TextView textLessonName = (TextView) convertView.findViewById(R.id.list_item_lesson_name_text);
        TextView textTeacherName = (TextView) convertView.findViewById(R.id.list_item_teacher_name_text);
        TextView textRoomName = (TextView) convertView.findViewById(R.id.list_item_lesson_room_type_text);
        TextView textLessonNum = (TextView) convertView.findViewById(R.id.list_item_lesson_num);

        /*
        CurrentTime currentTime = new CurrentTime();
        if (lessonsList.get(groupPosition).size() != 0 && groupPosition == currentTime.getCurrentWeekDay()){

            String timeTest = "12:30:00";
            try {
                compareDate(lessonsList.get(groupPosition).get(childPosition).getLessonStartDate(),lessonsList.get(groupPosition).get(childPosition).getLessonEndDate(), timeTest,convertView);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        */

        if (lessonsList.get(groupPosition).size() != 0){
            textLessonName.setText(lessonsList.get(groupPosition).get(childPosition).getLessonName());
            textTeacherName.setText(lessonsList.get(groupPosition).get(childPosition).getLessonTeacherName());
            textRoomName.setText(lessonsList.get(groupPosition).get(childPosition).getLessonRoom() + " " + lessonsList.get(groupPosition).get(childPosition).getLessonType());
            textLessonNum.setText(lessonsList.get(groupPosition).get(childPosition).getLessonNum());
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private void compareDate(String startTime, String endTime, String testTime, View convertView) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        //Date currentTime = new Date();
        //Date current = dateFormat.parse(dateFormat.format(currentTime));


        String dateInString = "10:00:00";

        try {
            Date startTimeD = dateFormat.parse(startTime);
            Date endTimeD = dateFormat.parse(endTime);
            Date currentTimeD = dateFormat.parse(dateInString);
            if (currentTimeD.after(startTimeD) && currentTimeD.before(endTimeD)){
                convertView.setBackgroundResource(R.color.sandy);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

}