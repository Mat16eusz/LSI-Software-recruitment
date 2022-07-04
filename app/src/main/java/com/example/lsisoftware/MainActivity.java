package com.example.lsisoftware;

import static com.example.lsisoftware.APIClient.getClient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> id = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getUsersDataOne();
        getUsersDataTwo();
    }

    private void getUsersDataOne() {
        APIService apiService = getClient(APIService.BASE_ONE_URL).create(APIService.class);

        Call<UsersDataAPIOne> call = apiService.getUsersDataOne();
        call.enqueue(new Callback<UsersDataAPIOne>() {
            @Override
            public void onResponse(Call<UsersDataAPIOne> call, Response<UsersDataAPIOne> response) {
                if (response.code() > 399) {
                    finish();
                } else {
                    UsersDataAPIOne usersDataAPISOne = response.body();

                    for (int i = 0; i < usersDataAPISOne.getLimit(); i++) {

                    }
                }
            }

            @Override
            public void onFailure(Call<UsersDataAPIOne> call, Throwable t) {
                Log.e("Retrofit", "Error: ", t);
            }
        });
    }

    private void getUsersDataTwo() {
        APIService apiService = getClient(APIService.BASE_TWO_URL).create(APIService.class);

        Call<List<UsersDataAPITwo>> call = apiService.getUsersDataTwo();
        call.enqueue(new Callback<List<UsersDataAPITwo>>() {
            @Override
            public void onResponse(Call<List<UsersDataAPITwo>> call, Response<List<UsersDataAPITwo>> response) {
                if (response.code() > 399) {
                    finish();
                } else {
                    List<UsersDataAPITwo> usersDataAPITwo = response.body();

                    for (UsersDataAPITwo userDataAPITwo : usersDataAPITwo) {

                    }
                }
            }

            @Override
            public void onFailure(Call<List<UsersDataAPITwo>> call, Throwable t) {
                Log.e("Retrofit", "Error: ", t);
            }
        });
    }
}