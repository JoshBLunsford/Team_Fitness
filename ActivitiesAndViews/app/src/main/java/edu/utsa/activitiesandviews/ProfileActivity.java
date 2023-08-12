package edu.utsa.activitiesandviews;

import android.content.Intent;
import android.content.res.AssetManager;
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
import edu.utsa.activitiesandviews.ui.theme.Calories;

public class ProfileActivity extends ComponentActivity {

    private Account profileinfo;
    private Calories calorieLog;
    //private AssetManager assets;

    @Override
    public void onCreate(Bundle savedInstancedState) {
        super.onCreate(savedInstancedState);
        setContentView(R.layout.profile);
        profileinfo = null;
        calorieLog = null;
        //assets = getAssets();
        setupProfile();
        setupButtons();
    }

    public void setupProfile() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);

        //profileinfo = new Account(id, assets);
        File f = new File(getFilesDir().getAbsolutePath() + "/accounts.txt");
        Scanner scan;
        String str = "";
        String[] arr = null;

        try {
            if (f.exists()) {
                scan = new Scanner(openFileInput("accounts.txt"));
                while (scan.hasNext()) {
                    str = scan.nextLine();
                    arr = str.split(",");
                    if (Integer.parseInt(arr[0]) == id) {
                        profileinfo = new Account(id, arr[1], arr[2]);
                        break;
                    }
                }
                scan.close();
            }
        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }

        if (profileinfo != null) {
            TextView name = (TextView) findViewById(R.id.Tname);
            TextView email = (TextView) findViewById(R.id.Temail);
            name.setText(profileinfo.getName());
            email.setText(profileinfo.getEmail());
        }
    }

    public void setupCalories() {
        Intent intent = getIntent();
        int calories = intent.getIntExtra("calories", 0);

        //profileinfo = new Account(id, assets);
        File f = new File(getFilesDir().getAbsolutePath() + "/calories.txt");
        Scanner scan;
        String str = "";
        String[] arr = null;

        try {
            if (f.exists()) {
                scan = new Scanner(openFileInput("calories.txt"));
                while (scan.hasNext()) {
                    str = scan.nextLine();
                    arr = str.split(",");
                    if (arr.length > 0 && Integer.parseInt(arr[0]) == calories) {
                        calorieLog = new Calories(Integer.parseInt(arr[0]));
                        break;
                    }
                }
                scan.close();
            }
        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }

        if (calorieLog != null) {
            TextView cal = (TextView) findViewById(R.id.Tcal);
            cal.setText(calorieLog.getCals());
        }
    }

    private void setupButtons() {
        Button caloriesbutton = (Button) findViewById(R.id.caloriesButton);
        Button workoutbutton = (Button) findViewById(R.id.workoutButton);
        caloriesbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MacroLogActivity.class);
                startActivity(intent);
            }
        });
            workoutbutton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(ProfileActivity.this, WorkoutLogActivity.class);
                    startActivity(intent);
                }
        });
    }
}
