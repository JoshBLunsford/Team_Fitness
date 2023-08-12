package edu.utsa.activitiesandviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.ComponentActivity;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import edu.utsa.activitiesandviews.ui.theme.Account;

public class WorkoutLogActivity extends ComponentActivity {

    private Account weightinfo;

    @Override
    public void onCreate(Bundle savedInstancedState) {
        super.onCreate(savedInstancedState);
        setContentView(R.layout.workoutlog);
        weightinfo = null;
        //assets = getAssets();
        setupworkoutLog();
        setupButtons();
    }

    private void setupworkoutLog() {

    }

    private void setupButtons() {
        Button submitWorkout = (Button) findViewById(R.id.submitworkout);
        submitWorkout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*EditText submitWorkout = (EditText) findViewById(R.id.Tweight); */
                Intent intent = new Intent(WorkoutLogActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}
