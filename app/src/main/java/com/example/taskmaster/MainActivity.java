package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    AppDatabase appDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //////////////////////////////////////// lab 27 //////////////////////////////////
        Button settings = findViewById(R.id.homeSettingsBtn);
        Button addTask = findViewById(R.id.homeAddtaskBtn);

        // listners
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSettings= new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(goToSettings);
            }
        });

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddTaskPage= new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(goToAddTaskPage);
            }
        });

        //////////////////////////////////////// lab 29 //////////////////////////////////
        // tasks from database
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "tasks").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        List<Task> allTasks = appDatabase.taskDao().getAll();

        // get recycler view
        RecyclerView allTasksRecyclerView = findViewById(R.id.taskListRecyclerView);

        // set layout manager for the view (determine if liner list or grid list)
        allTasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // set the adapter for this recycler
        allTasksRecyclerView.setAdapter(new TaskAdapter(allTasks));
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String userName = sharedPreferences.getString("userName","X");

        TextView nameView = findViewById(R.id.homeUserTasksTitle);
        nameView.setText(userName + " tasks");
    }
}