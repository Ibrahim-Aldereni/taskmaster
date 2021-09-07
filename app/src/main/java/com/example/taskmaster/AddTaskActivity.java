package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;

public class AddTaskActivity extends AppCompatActivity {
//    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        //////////////////////////////////// save to database ///////////////////////////////
        // get all edit text data
        EditText taskTitle = findViewById(R.id.titleField);
        EditText taskBody = findViewById(R.id.bodyField);
        EditText taskState = findViewById(R.id.stateField);
        // get add task button
        Button addTaskBtn = findViewById(R.id.addTaskBtn);
        // add listener
        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = taskTitle.getText().toString();
                String body = taskBody.getText().toString();
                String state = taskState.getText().toString();

//                appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "tasks").allowMainThreadQueries().fallbackToDestructiveMigration().build();
//                // save input fields into object
//                Task task = new Task(title,body,state);
//                // save to db
//                appDatabase.taskDao().insertAll(task);

      //////////////////////////////////////////////// lab 32 //////////////////////////////////////////
                // save data to data base
                Task task = Task.builder()
                        .title(title)
                        .body(body)
                        .status(state)
                        .build();

                Amplify.API.mutate(
                        ModelMutation.create(task),
                        response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
                        error -> Log.e("MyAmplifyApp", "Create failed", error)
                );

                // redirect to menu page
                Intent goToHomePage = new Intent(AddTaskActivity.this, MainActivity.class);
                startActivity(goToHomePage);
            }
        });
    }
}