package com.example.lsisoftware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user);

        Intent intent = getIntent();
        String avatar = intent.getStringExtra("KEY_AVATAR");
        String name = intent.getStringExtra("KEY_NAME");
        String sourceAPI = intent.getStringExtra("KEY_SOURCE_API");

        TextView nameShowDetails = findViewById(R.id.nameShowDetails);
        TextView sourceAPIShowDetails = findViewById(R.id.sourceAPIShowDetails);
        nameShowDetails.setText(name);
        sourceAPIShowDetails.setText(sourceAPI);
    }
}