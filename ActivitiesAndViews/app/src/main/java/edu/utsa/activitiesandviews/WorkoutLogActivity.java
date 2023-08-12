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
    private Workout workoutinfo;

    @Override
    public void onCreate(Bundle savedInstancedState) {
        super.onCreate(savedInstancedState);
        setContentView(R.layout.workoutlog);
        weightinfo = null;
        //assets = getAssets();
        setupworkoutLog();
        setupButtons();
    }

    private int createworkoutLog(){
        EditText musInput = (EditText) findViewById(R.id.MusInput);
        EditText exInput = (EditText) findViewById(R.id.ExInput);
        String muscle = musInput.getTExt().toString();
        String exercise = exInput.getText().toString();

        File f = new File(getFilesDir().getAbsolutePath() + "/workoutlog.txt");
        OutputStreamWriter w;
        int id = -1;
        Scanner scan;
        String record = null;
        String[] arr;

        if (!f.exists()) {
            id = 1;
            try {
                w = new OutputStreamWriter(openFileOutput("workoutlog.txt", MODE_PRIVATE));
                w.write(id, muscle, exercise);
                w.close();
            } catch (IOException e) {
                Toast.makeText(getBaseContent(), "IOException" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else {
            try {
                scan = new Scanner(openFileInput("workoutlog.txt"));
                while (scan.hasNext()) {
                    record = scanNextLine();
                }
                if (record != null) {
                    arr = record.split(",");
                    if (arr.length == 3) {
                        id = Integer.parseInt(arr[0]) + 1;
                    }
                }
                scan.close();

                w = new OutputStreamWriter(openFileOutput("workoutlog.txt"));
                w.append("\n" + id + "," + muscle + "," + exercise);
                w.close();
            } catch (IOException e) {
                Toast.makeText(getBaseContent(), "IOException", e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        return id;
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
