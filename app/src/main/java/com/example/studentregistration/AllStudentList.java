package com.example.studentregistration;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentregistration.database.MainData;
import com.example.studentregistration.database.RoomDB;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class AllStudentList extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    RecyclerView recyclerView;
    List<MainData> dataList = new ArrayList<>();
    RoomDB database;
    MainAdapter mainAdapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_student_list);
        Toolbar toolbarList = findViewById(R.id.toolbarList);
        setSupportActionBar(toolbarList);



        recyclerView = findViewById(R.id.recycleView);
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.listItem);

        database = RoomDB.getInstance(AllStudentList.this);
        dataList = database.mainDao().getAll();
        //Initialise the linear layout manager
        linearLayoutManager = new LinearLayoutManager(AllStudentList.this);
        //Set layout manager
        recyclerView.setLayoutManager(linearLayoutManager);
        //initialize adapter
        mainAdapter = new MainAdapter(AllStudentList.this, dataList);
        recyclerView.setAdapter(mainAdapter);



        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.addItem:
                                Intent i = new Intent(AllStudentList.this, AddStudentData.class);
                                startActivity(i);
                                overridePendingTransition(0,0);
                                return true;

                            case R.id.home:
                                Intent intent = new Intent(AllStudentList.this, MainActivity.class);
                                startActivity(intent);
                                overridePendingTransition(0,0);
                                return true;


                        }
                        return false;
                    }
                });
    }
}