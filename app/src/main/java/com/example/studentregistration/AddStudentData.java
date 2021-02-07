package com.example.studentregistration;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.studentregistration.database.MainData;
import com.example.studentregistration.database.RoomDB;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class AddStudentData extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Button buttonAdd;
    RoomDB database;
    List<MainData> dataList = new ArrayList<>();
    MainAdapter mainAdapter;
    EditText studentName, studentNumber, studentEmail, studentPhoneNumber, studentAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_data);
        Toolbar toolbarAdd = findViewById(R.id.toolbarAdd);
        setSupportActionBar(toolbarAdd);

        studentName = findViewById(R.id.studentName);
        studentNumber = findViewById(R.id.studentNumber);
        studentEmail = findViewById(R.id.studentEmail);
        studentAddress = findViewById(R.id.studentAddress);
        studentPhoneNumber = findViewById(R.id.studentMobile);
        buttonAdd = findViewById(R.id.btnAdd);
        bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setSelectedItemId(R.id.addItem);



        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.listItem:
                                Intent i = new Intent(AddStudentData.this, AllStudentList.class);
                                startActivity(i);
                                overridePendingTransition(0,0);
                                return true;

                            case R.id.home:
                                Intent intent = new Intent(AddStudentData.this, MainActivity.class);
                                startActivity(intent);
                                overridePendingTransition(0,0);
                                return true;


                        }
                        return false;
                    }
                });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainData mainData = new MainData();
                mainData.setUserName(studentName.getText().toString());
                mainData.setStudentNumber(studentNumber.getText().toString());
                mainData.setStudentEmail(studentEmail.getText().toString());
                mainData.setPhoneNumber(studentPhoneNumber.getText().toString());
                mainData.setStudentAddress(studentAddress.getText().toString());

                database = RoomDB.getInstance(AddStudentData.this);
                //insert username to database
                database.mainDao().insert(mainData);

                //Clear edit text
                studentName.setText("");

                //Notify when data is inserted
                dataList.clear();
                dataList.addAll(database.mainDao().getAll());

                //initialize adapter
                mainAdapter = new MainAdapter(AddStudentData.this, dataList);
                mainAdapter.notifyDataSetChanged();
                Intent intent = new Intent(AddStudentData.this, AllStudentList.class);
                startActivity(intent);
            }
        });
    }

}