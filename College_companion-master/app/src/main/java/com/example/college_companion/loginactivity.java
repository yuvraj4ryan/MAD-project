package com.example.college_companion;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);

        // Find views
        EditText editTextUsername = findViewById(R.id.editTextUsername);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        Button loginButton = findViewById(R.id.buttonLogin);

        // Set click listener for the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered username and password
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                // Validate the username and password
                if (isValidCredentials(username, password)) {
                    Intent intent = new Intent(loginactivity.this, dashboardActivity.class);
                    startActivity(intent);
                } else {
                    // Display an error message if credentials are invalid
                    Toast.makeText(loginactivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Method to validate username and password
    private boolean isValidCredentials(String username, String password) {
        // Validate username (11 digits number)
        if (username.length() != 11 || !isNumeric(username)) {
            return false;
        }
        // Validate password (minimum 8 characters with at least 1 special character)
        if (password.length() < 8 || !containsSpecialCharacter(password)) {
            return false;
        }
        return true;
    }

    // Method to check if a string is numeric
    private boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    // Method to check if a string contains at least 1 special character
    private boolean containsSpecialCharacter(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                return true;
            }
        }
        return false;
}
}
