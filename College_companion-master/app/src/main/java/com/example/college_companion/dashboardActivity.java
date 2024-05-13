package com.example.college_companion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class dashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Set click listeners for buttons
        ImageButton attendanceButton = findViewById(R.id.attendanceButton);
        ImageButton timetableButton = findViewById(R.id.timetableButton);
        ImageButton assignmentButton = findViewById(R.id.assignmentButton);
        ImageButton resultsButton = findViewById(R.id.resultsButton);

        attendanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboardActivity.this, AttendanceActivity.class));
            }
        });

        timetableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboardActivity.this, TimetableActivity.class));
            }
        });

        assignmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboardActivity.this, AssignmentActivity.class));
            }
        });

        resultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboardActivity.this, ResultsActivity.class));
            }
   });
}
}
