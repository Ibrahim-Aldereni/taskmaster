package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//    AppDatabase appDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     //////////////////////////////////////// lab 32 //////////////////////////////////
        try {
            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());

            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }

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
//        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "tasks").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        List<Task> allTasks = new ArrayList<>();
        // get recycler view
        RecyclerView allTasksRecyclerView = findViewById(R.id.taskListRecyclerView);
        // set layout manager for the view (determine if liner list or grid list)
        allTasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // set the adapter for this recycler
        allTasksRecyclerView.setAdapter(new TaskAdapter(allTasks));

        ///////////////////////////////////// lab 32 //////////////////////////////////////
        // handler
        Handler handler = new Handler(Looper.myLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                allTasksRecyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });

        // get data from database
        Amplify.API.query(
                ModelQuery.list(Task.class),
                response -> {
                    for (Task task : response.getData()) {
//                        Log.i("MyAmplifyApp", task.getTitle());
                        allTasks.add(task);
                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
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