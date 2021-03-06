package com.example.lsisoftware;

import static com.example.lsisoftware.APIClient.getClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lsisoftware.database.AppDatabase;
import com.example.lsisoftware.database.User;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SelectListener {

    private UserListAdapter userListAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getUsersDataOne();
        getUsersDataTwo();

        initRecyclerView();

        loadUserList();
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
                        saveUser(usersDataAPISOne.getId(i), usersDataAPISOne.getScreenname(i), APIService.BASE_ONE_URL + "users");
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
                        saveUser(userDataAPITwo.getLogin(), userDataAPITwo.getAvatar_url(), APIService.BASE_TWO_URL + "users");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<UsersDataAPITwo>> call, Throwable t) {
                Log.e("Retrofit", "Error: ", t);
            }
        });
    }

    private void saveUser(String name, String avatar, String sourceAPI) {
        AppDatabase appDatabase = AppDatabase.getDbInstance(this.getApplicationContext());

        User user = new User();
        user.name = name;
        user.avatar = avatar;
        user.sourceAPI = sourceAPI;

        appDatabase.userDAO().insertUser(user);
    }

    private void loadUserList() {
        AppDatabase appDatabase = AppDatabase.getDbInstance(this.getApplicationContext());

        List<User> userList = appDatabase.userDAO().getAllUsers();
        userListAdapter.setUserList(userList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 100) {
            loadUserList();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        userListAdapter = new UserListAdapter(this, this);
        recyclerView.setAdapter(userListAdapter);
    }

    @Override
    public void onItemClicked(UserListAdapter.MyViewHolder myViewHolder) {
        Intent intent = new Intent(MainActivity.this, DisplayUserActivity.class);

        intent.putExtra("KEY_AVATAR", myViewHolder.avatar.toString());
        intent.putExtra("KEY_NAME", myViewHolder.name.getText());
        intent.putExtra("KEY_SOURCE_API", myViewHolder.sourceAPI.getText());

        MainActivity.this.startActivity(intent);
    }
}