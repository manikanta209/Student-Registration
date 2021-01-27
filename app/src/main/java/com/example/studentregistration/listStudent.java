package com.example.studentregistration;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentregistration.database.MainData;
import com.example.studentregistration.database.RoomDB;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class listStudent extends Fragment {
    RecyclerView recyclerView;
    List<MainData> dataList = new ArrayList<>();
    RoomDB database;
    MainAdapter mainAdapter;
    LinearLayoutManager linearLayoutManager;

    public listStudent() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recyclerView = getView().findViewById(R.id.recycleView);
        database = RoomDB.getInstance(getContext());
        dataList = database.mainDao().getAll();

        //Initialise the linear layout manager
        linearLayoutManager = new LinearLayoutManager(getContext());
        //Set layout manager
        recyclerView.setLayoutManager(linearLayoutManager);
        //initialize adapter
//        mainAdapter = new MainAdapter(listStudent.this, dataList);
//        recyclerView.setAdapter(mainAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_list_student, container, false);
    }


}