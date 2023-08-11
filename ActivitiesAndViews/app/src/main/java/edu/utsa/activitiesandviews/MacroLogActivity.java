package edu.utsa.activitiesandviews;

import android.os.Bundle;

import androidx.activity.ComponentActivity;

public class MacroLogActivity extends ComponentActivity {

    @Override
    public void onCreate(Bundle savedInstancedState) {
        super.onCreate(savedInstancedState);
        setContentView(R.layout.calorielog);
        //assets = getAssets();
        setupcalorieLog();
        setupButtons();
    }

    private void setupcalorieLog() {
    }

    private void setupButtons() {
    }
}
