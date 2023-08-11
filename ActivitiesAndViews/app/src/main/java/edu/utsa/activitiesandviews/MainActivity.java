package edu.utsa.activitiesandviews;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.ComponentActivity;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MainActivity extends ComponentActivity {
    //private Button button;
    //private AssetManager assets;


    @Override
    public void onCreate(Bundle savedInstancedState) {
        super.onCreate(savedInstancedState);
        setContentView(R.layout.login);
        //assets = getAssets();
        Toast.makeText(this, "HELLO.", Toast.LENGTH_LONG).show();
        setupButtons();
    }

    private void setupButtons() {
        Button logbutton = (Button) findViewById(R.id.login);
        Button regbutton = (Button) findViewById((R.id.register));
        logbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText uText = (EditText) findViewById(R.id.InputName);
                EditText pText = (EditText) findViewById((R.id.InputPassword));
                int id = authenticate(uText.getText().toString(), pText.getText().toString());
                if (id > 0) {
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                } else {
                    uText.setText("");
                    pText.setText("");
                    uText.setError("Incorrect username and password combination.");
                    pText.setError("Incorrect username and password combination");
                }
            }
        });
        regbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private int authenticate(String username, String password) {
        Scanner scan;
        String str = "";
        String[] arr = null;
        boolean authenticated = false;
        int id = -1;
        File f = new File(getFilesDir().getAbsolutePath() + "/login.txt");

        try {
            if (f.exists()) {
                scan = new Scanner(openFileInput("login.txt"));
                while (scan.hasNext()) {
                    str = scan.nextLine();
                    arr = str.split(",");
                    if (username.equalsIgnoreCase(arr[1]) && password.equals(arr[2])) {
                        authenticated = true;
                        id = Integer.parseInt(arr[0]);
                        break;
                    }

                }
                scan.close();
            }
        } catch (IOException e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT);
        }

        return id;

    }

}