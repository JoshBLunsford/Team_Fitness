package edu.utsa.activitiesandviews;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.ComponentActivity;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import edu.utsa.activitiesandviews.ui.theme.Account;

public class RegisterActivity extends ComponentActivity {

    public void onCreate(Bundle savedInstancedState) {
        super.onCreate(savedInstancedState);
        setContentView(R.layout.registration);
        //Toast.makeText(this,"HELLO.", Toast.LENGTH_LONG).show();
        setupButtons();
    }
    private void setupButtons() {
        Button logbutton = (Button) findViewById(R.id.submitRegister);
        logbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int id = -1;
                EditText unameInput = (EditText) findViewById(R.id.register_unameinput);
                EditText passInput = (EditText) findViewById(R.id.register_passinput);
                EditText nameInput = (EditText) findViewById(R.id.register_nameinput);
                EditText emailInput = (EditText) findViewById(R.id.register_emailinput);

                if(validateAccountInfo()) {
                    id = createLogin();
                    if(id > 0){
                        createAccount(id);
                    }
                    finish();
                } else {
                    unameInput.setText("");
                    passInput.setText("");
                    nameInput.setText("");
                    emailInput.setText("");
                    unameInput.setError("All fields mys be filled out.");
                    passInput.setError("All fields mys be filled out.");
                    nameInput.setError("All fields mys be filled out.");
                    emailInput.setError("All fields mys be filled out.");
                }
            }
        });
    }

    private boolean validateAccountInfo(){
        EditText unameInput = (EditText) findViewById(R.id.register_unameinput);
        EditText passInput = (EditText) findViewById(R.id.register_passinput);
        EditText nameInput = (EditText) findViewById(R.id.register_nameinput);
        EditText emailInput = (EditText) findViewById(R.id.register_emailinput);

        if(!unameInput.getText().toString().equals("") && !passInput.getText().toString().equals("")
                && !nameInput.getText().toString().equals("") && !emailInput.getText().toString().equals("")) {
            return true;
        }
        return false;
        }

    private int createLogin() {
        EditText unameInput = (EditText) findViewById(R.id.register_unameinput);
        EditText passInput = (EditText) findViewById(R.id.register_passinput);
        String username = unameInput.getText().toString();
        String password = passInput.getText().toString();

        File f = new File(getFilesDir().getAbsolutePath() + "/login.txt");
        OutputStreamWriter w;
        Scanner scan;
        int id = -1;
        String str = null;
        String[] arr;

        if (!f.exists()) {
            id = 1;
            try {
                w = new OutputStreamWriter(openFileOutput("login.txt", MODE_PRIVATE));
                w.write(id + "," + username + "," + password);
                w.close();
            }
            catch (IOException e) {
                Toast.makeText(getBaseContext(), "IOException" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            try {
                scan = new Scanner(openFileInput("login.txt"));
                while (scan.hasNext()) {
                    str = scan.nextLine();
                }
                if (str != null) {
                    arr = str.split(",");
                    if (arr.length == 3) {
                        id = Integer.parseInt(arr[0]) + 1;
                    }
                }
                scan.close();

                w = new OutputStreamWriter(openFileOutput("login.txt", MODE_APPEND));
                w.append("\n" + id + "," + username + "," + password);
                w.close();
            }
            catch (IOException e) {
                Toast.makeText(getBaseContext(), "IOException" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        return id;

    }

    private void createAccount(int id){
        EditText nameInput = (EditText) findViewById(R.id.register_nameinput);
        EditText emailInput = (EditText) findViewById(R.id.register_emailinput);
        String name = nameInput.getText().toString();
        String email = emailInput.getText().toString();

        File f = new File(getFilesDir().getAbsolutePath() + "/accounts.txt");
        OutputStreamWriter w = null;

        if (!f.exists()) {
            try {
                w = new OutputStreamWriter(openFileOutput("accounts.txt", MODE_PRIVATE));
                w.write(id + "," + name + "," + email);
                w.close();
            }
            catch (IOException e) {
                Toast.makeText(getBaseContext(), "IOException" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else {
            try {
            w = new OutputStreamWriter(openFileOutput("accounts.txt", MODE_APPEND));
            w.append("\n" + id + "," + name + "," + email);
            w.close();
            }
            catch (IOException e) {
                Toast.makeText(getBaseContext(), "IOException" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
