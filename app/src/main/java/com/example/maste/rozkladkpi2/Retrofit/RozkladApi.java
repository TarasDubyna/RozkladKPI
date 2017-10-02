package com.example.maste.rozkladkpi2.Retrofit;

import com.example.maste.rozkladkpi2.Retrofit.Model.AllLessonsModel.AllLessonsModel;
import com.example.maste.rozkladkpi2.Retrofit.Model.CheckGroupModel.CorrectGroupModel;
import com.example.maste.rozkladkpi2.Retrofit.Model.WeekNumberModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface RozkladApi {

    //Получить все занятия определенной группы
    @GET("v2/groups/{group_name}/lessons")
    Call<AllLessonsModel> getAllLessons(@Path("group_name") String group_name);


    //Получить номер недели
    @GET("v2/weeks")
    Call<WeekNumberModel> getWeekNumber();

    //Получить(проверка) группу
    @GET("v2/groups/{group_name}")
    Call<CorrectGroupModel> getCheckGroup(@Path("group_name") String group_name);


}
