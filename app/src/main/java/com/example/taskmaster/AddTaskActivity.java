package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.AsyncQueryHandler;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.amazonaws.handlers.AsyncHandler;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;

import java.util.concurrent.atomic.AtomicReference;

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

                //////////////////////////////////////////////// lab 32 and lab33 //////////////////////////////////////////
                // get radio button name
                RadioGroup radioGroup = findViewById(R.id.radioGroup);
                int radioButtonId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(radioButtonId);
                String name = radioButton.getText().toString();

                // get selected team id from database and save task data to database
                StringBuilder teamId = new StringBuilder();

                Amplify.API.query(
                        ModelQuery.list(Team.class),
                        response -> {
                            for (Team team : response.getData()) {
                                if (team.getName().equals(name)) {
                                    teamId.append(team.getId());
                                }
                            }
                            // save task to database
                            Task task = Task.builder()
                                    .teamId(teamId.toString())
                                    .title(title)
                                    .body(body)
                                    .status(state)
                                    .build();

                            Amplify.API.mutate(
                                    ModelMutation.create(task),
                                    response2 -> Log.i("MyAmplifyApp", "Added Task with id: " + response2.getData().getId()),
                                    error -> Log.e("MyAmplifyApp", "Create failed", error)
                            );
                        },
                        error -> Log.e("MyAmplifyApp", "Query failure", error)
                );

                // redirect to menu page
                Intent goToHomePage = new Intent(AddTaskActivity.this, MainActivity.class);
                startActivity(goToHomePage);
            }
        });
    }
}