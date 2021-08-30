package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // get the save button
        Button saveBtn = findViewById(R.id.settingsSaveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // intilize shared prefernces
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);
                SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();

                // get user name
                EditText userName = findViewById(R.id.settingsTextField);
                String name = userName.getText().toString();

                // save to shared prefrences
                sharedPreferencesEditor.putString("userName",name);
                sharedPreferencesEditor.apply();
            }
        });
    }
}