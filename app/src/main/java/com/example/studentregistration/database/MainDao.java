package com.example.studentregistration.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MainDao {

    @Insert
    void insert(MainData mainData);

    @Delete
    void delete(MainData mainData);

    @Query("UPDATE studentdata SET UserName = :sUsername WHERE AutoID = :sAutoID")
    void update(int sAutoID, String sUsername);

    @Query("SELECT * FROM STUDENTDATA")
    List<MainData> getAll();

}
