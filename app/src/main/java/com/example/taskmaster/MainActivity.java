package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get buttons
        Button addTaskBtn = findViewById(R.id.addTaskBtn);
        Button allTaskBtn = findViewById(R.id.allTasksBtn);

        // click listeners
        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddTask = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(goToAddTask);
            }
        });

        allTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAllTasks = new Intent(MainActivity.this, AllTasksActivity.class);
                startActivity(goToAllTasks);
            }
        });

        ////////////////////////////////////////// lab 27 //////////////////////////////////////////
        // get buttons
        Button task1Btn = findViewById(R.id.homeTask1Btn);
        Button task2Btn = findViewById(R.id.homeTask2Btn);
        Button task3Btn = findViewById(R.id.homeTask3Btn);
        Button settings = findViewById(R.id.homeSettingsBtn);

        // listners
        task1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToTaskDetail = new Intent(MainActivity.this, TaskDetailActivity.class);
                String btnText = task1Btn.getText().toString();
                goToTaskDetail.putExtra("task",btnText);
                startActivity(goToTaskDetail);
            }
        });
        task2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToTaskDetail = new Intent(MainActivity.this, TaskDetailActivity.class);
                String btnText = task2Btn.getText().toString();
                goToTaskDetail.putExtra("task",btnText);
                startActivity(goToTaskDetail);
            }
        });
        task3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToTaskDetail = new Intent(MainActivity.this, TaskDetailActivity.class);
                String btnText = task3Btn.getText().toString();
                goToTaskDetail.putExtra("task",btnText);
                startActivity(goToTaskDetail);
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSettings= new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(goToSettings);
            }
        });
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