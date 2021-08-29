package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
                Intent goToAddTask = new Intent(MainActivity.this,AddTaskActivity.class);
                startActivity(goToAddTask);
            }
        });

        allTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAllTasks = new Intent(MainActivity.this,AllTasksActivity.class);
                startActivity(goToAllTasks);
            }
        });
    }
}