package edu.utsa.activitiesandviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.ComponentActivity;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

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
        EditText calInput = (EditText) findViewById(R.id.calInput);
        try {
            int calories = Integer.parseInt(calInput.getText().toString());

            File f = new File(getFilesDir().getAbsolutePath() + "/calories.txt");
            OutputStreamWriter w = null;

            if (!f.exists()) {
                try {
                    w = new OutputStreamWriter(openFileOutput("calories.txt", MODE_PRIVATE));
                    w.write(Integer.toString(calories));
                    w.close();
                } catch (IOException e) {
                    Toast.makeText(getBaseContext(), "IOException" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            } else {
                try {
                    w = new OutputStreamWriter(openFileOutput("calories.txt", MODE_APPEND));
                    w.append(Integer.toString(calories));
                    w.close();
                } catch (IOException e) {
                    Toast.makeText(getBaseContext(), "IOException" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
        catch (NumberFormatException e) {
            Toast.makeText(getBaseContext(), "Invalid input." + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void setupButtons() {
        Button submitCal = (Button) findViewById(R.id.submitCal);
        submitCal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int cal = 0;
                EditText calInput = (EditText) findViewById(R.id.calInput);
                finish();
            }
        });
    }
}
