package com.example.college_companion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AttendanceActivity extends AppCompatActivity {

    EditText editTextSubject1TotalHours, editTextSubject1CurrentHours;
    EditText editTextSubject2TotalHours, editTextSubject2CurrentHours;
    EditText editTextSubject3TotalHours, editTextSubject3CurrentHours;
    EditText editTextSubject4TotalHours, editTextSubject4CurrentHours;
    EditText editTextSubject5TotalHours, editTextSubject5CurrentHours;

    TextView subject1CurrentPercentageTextView, subject1ClassesYouCanMissTextView;
    TextView subject2CurrentPercentageTextView, subject2ClassesYouCanMissTextView;
    TextView subject3CurrentPercentageTextView, subject3ClassesYouCanMissTextView;
    TextView subject4CurrentPercentageTextView, subject4ClassesYouCanMissTextView;
    TextView subject5CurrentPercentageTextView, subject5ClassesYouCanMissTextView;

    Button calculateButton;
    Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        // Initialize views
        editTextSubject1TotalHours = findViewById(R.id.editTextSubject1TotalHours);
        editTextSubject1CurrentHours = findViewById(R.id.editTextSubject1CurrentHours);
        editTextSubject2TotalHours = findViewById(R.id.editTextSubject2TotalHours);
        editTextSubject2CurrentHours = findViewById(R.id.editTextSubject2CurrentHours);
        editTextSubject3TotalHours = findViewById(R.id.editTextSubject3TotalHours);
        editTextSubject3CurrentHours = findViewById(R.id.editTextSubject3CurrentHours);
        editTextSubject4TotalHours = findViewById(R.id.editTextSubject4TotalHours);
        editTextSubject4CurrentHours = findViewById(R.id.editTextSubject4CurrentHours);
        editTextSubject5TotalHours = findViewById(R.id.editTextSubject5TotalHours);
        editTextSubject5CurrentHours = findViewById(R.id.editTextSubject5CurrentHours);

        subject1CurrentPercentageTextView = findViewById(R.id.subject1CurrentPercentageTextView);
        subject1ClassesYouCanMissTextView = findViewById(R.id.subject1ClassesYouCanMissTextView);
        subject2CurrentPercentageTextView = findViewById(R.id.subject2CurrentPercentageTextView);
        subject2ClassesYouCanMissTextView = findViewById(R.id.subject2ClassesYouCanMissTextView);
        subject3CurrentPercentageTextView = findViewById(R.id.subject3CurrentPercentageTextView);
        subject3ClassesYouCanMissTextView = findViewById(R.id.subject3ClassesYouCanMissTextView);
        subject4CurrentPercentageTextView = findViewById(R.id.subject4CurrentPercentageTextView);
        subject4ClassesYouCanMissTextView = findViewById(R.id.subject4ClassesYouCanMissTextView);
        subject5CurrentPercentageTextView = findViewById(R.id.subject5CurrentPercentageTextView);
        subject5ClassesYouCanMissTextView = findViewById(R.id.subject5ClassesYouCanMissTextView);

        calculateButton = findViewById(R.id.calculateButton);
        buttonBack = findViewById(R.id.buttonBack); // Initialize buttonBack

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the dashboard activity
                Intent intent = new Intent(AttendanceActivity.this, dashboardActivity.class);
                startActivity(intent);
                finish(); // Finish the current activity
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAndUpdateAttendance();
            }
        });
    }

    private void calculateAndUpdateAttendance() {
        calculateAndUpdateSubjectAttendance(editTextSubject1TotalHours, editTextSubject1CurrentHours,
                subject1CurrentPercentageTextView, subject1ClassesYouCanMissTextView);
        calculateAndUpdateSubjectAttendance(editTextSubject2TotalHours, editTextSubject2CurrentHours,
                subject2CurrentPercentageTextView, subject2ClassesYouCanMissTextView);
        calculateAndUpdateSubjectAttendance(editTextSubject3TotalHours, editTextSubject3CurrentHours,
                subject3CurrentPercentageTextView, subject3ClassesYouCanMissTextView);
        calculateAndUpdateSubjectAttendance(editTextSubject4TotalHours, editTextSubject4CurrentHours,
                subject4CurrentPercentageTextView, subject4ClassesYouCanMissTextView);
        calculateAndUpdateSubjectAttendance(editTextSubject5TotalHours, editTextSubject5CurrentHours,
                subject5CurrentPercentageTextView, subject5ClassesYouCanMissTextView);
    }

    private void calculateAndUpdateSubjectAttendance(EditText totalHoursEditText, EditText currentHoursEditText,
                                                     TextView currentPercentageTextView, TextView classesYouCanMissTextView) {
        int totalHours = Integer.parseInt(totalHoursEditText.getText().toString());
        int currentHours = Integer.parseInt(currentHoursEditText.getText().toString());

        // Calculate current percentage
        double currentPercentage = (double) currentHours / totalHours * 100;
        currentPercentageTextView.setText(String.format("%.1f%%", currentPercentage));

        // Calculate classes a student can miss to maintain 80% attendance
        int classesYouCanMiss = (int) Math.ceil(0.2 * totalHours) - (totalHours - currentHours);
        if (classesYouCanMiss < 0) {
            classesYouCanMiss = 0;
        }

        classesYouCanMissTextView.setText(String.valueOf(classesYouCanMiss));
    }

}
