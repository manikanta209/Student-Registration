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

    @ColumnInfo(name = "UserName")
    private String UserName;

    @ColumnInfo(name = "StudentNumber")
    private String StudentNumber;

    @ColumnInfo(name = "StudentEmail")
    private String StudentEmail;

    public String getStudentNumber() {
        return StudentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        StudentNumber = studentNumber;
    }

    public String getStudentEmail() {
        return StudentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        StudentEmail = studentEmail;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getStudentAddress() {
        return StudentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        StudentAddress = studentAddress;
    }

    @ColumnInfo(name = "PhoneNumber")
    private String PhoneNumber;

    @ColumnInfo(name = "StudentAddress")
    private String StudentAddress;

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
