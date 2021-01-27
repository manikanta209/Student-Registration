package com.example.studentregistration.database;

//Define table name

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "studentdata")
public class MainData implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int AutoID;

    @ColumnInfo(name = "firstname")
    private String UserName;

    public int getAutoID() {
        return AutoID;
    }

    public void setAutoID(int autoID) {
        AutoID = autoID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
}
