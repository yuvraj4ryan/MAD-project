package com.example.college_companion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);

        // Find views
        EditText editTextEmail = findViewById(R.id.editTextSAPID);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        Button loginButton = findViewById(R.id.buttonLogin);
        Button signupButton=findViewById(R.id.buttonSignup);
        FirebaseAuth mAuth;
// ...
// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // Set click listener for the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered SAP ID and password
                String Email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                // Validate the SAP ID and password
                if (isValidCredentials(Email, password)) {
                    mAuth.signInWithEmailAndPassword(Email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(loginactivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(loginactivity.this,profile.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });

                } else {
                    // Display an error message if credentials are invalid
                    Toast.makeText(loginactivity.this, "Invalid SAP ID or password", Toast.LENGTH_SHORT).show();
                }


            }
        });

        //to registration
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(loginactivity.this,signup.class);
                startActivity(intent);
            }
        });


    }





    // Method to validate SAP ID and password
    private boolean isValidCredentials(String Email, String password) {
        // Validate email id
        if (Email.isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            Toast.makeText(this, "error in email", Toast.LENGTH_SHORT).show();
            return false;
        }
        // Validate password (minimum 8 characters with at least 1 special character)
        if (password.length() < 8) {
            Toast.makeText(this, "error in password", Toast.LENGTH_SHORT).show();
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
