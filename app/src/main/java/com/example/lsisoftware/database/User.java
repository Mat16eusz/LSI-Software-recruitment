package com.example.lsisoftware.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "avatar")
    public String avatar;
    @ColumnInfo(name = "source_api")
    public String sourceAPI;
}
