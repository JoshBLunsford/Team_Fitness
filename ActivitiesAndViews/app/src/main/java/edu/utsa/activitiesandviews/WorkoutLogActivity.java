package edu.utsa.activitiesandviews;

import android.os.Bundle;

import androidx.activity.ComponentActivity;

public class WorkoutLogActivity extends ComponentActivity {

    @Override
    public void onCreate(Bundle savedInstancedState) {
        super.onCreate(savedInstancedState);
        setContentView(R.layout.workoutlog);
        //assets = getAssets();
        setupworkoutLog();
        setupButtons();
    }

    private void setupworkoutLog() {
    }

    private void setupButtons() {
    }
}
