package com.example.lsisoftware;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    String BASE_ONE_URL = "https://api.dailymotion.com/";
    String BASE_TWO_URL = "https://api.github.com/";

    @GET("users")
    Call<UsersDataAPIOne> getUsersDataOne();

    @GET("users")
    Call<List<UsersDataAPITwo>> getUsersDataTwo();
}
