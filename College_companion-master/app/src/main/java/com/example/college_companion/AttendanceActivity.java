package com.example.college_companion;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;




    public class AttendanceActivity extends AppCompatActivity {

        private EditText editTextSubject1Attendance, editTextSubject1Percentage, editTextSubject1MissMore;
        private EditText editTextSubject2Attendance, editTextSubject2Percentage, editTextSubject2MissMore;
        private EditText editTextSubject3Attendance, editTextSubject3Percentage, editTextSubject3MissMore;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_attendance);

            editTextSubject1Attendance = findViewById(R.id.editTextSubject1Attendance);
            editTextSubject1Percentage = findViewById(R.id.editTextSubject1Percentage);
            editTextSubject1MissMore = findViewById(R.id.editTextSubject1MissMore);

            editTextSubject2Attendance = findViewById(R.id.editTextSubject2Attendance);
            editTextSubject2Percentage = findViewById(R.id.editTextSubject2Percentage);
            editTextSubject2MissMore = findViewById(R.id.editTextSubject2MissMore);

            editTextSubject3Attendance = findViewById(R.id.editTextSubject3Attendance);
            editTextSubject3Percentage = findViewById(R.id.editTextSubject3Percentage);
            editTextSubject3MissMore = findViewById(R.id.editTextSubject3MissMore);

            // Set sample data
            setSampleData();
        }

        private void setSampleData() {
            // Replace with actual data
            editTextSubject1Attendance.setText("18/20");
            editTextSubject1Percentage.setText("90.0");
            editTextSubject1MissMore.setText("3");

            editTextSubject2Attendance.setText("14/20");
            editTextSubject2Percentage.setText("70.0");
            editTextSubject2MissMore.setText("6");

            editTextSubject3Attendance.setText("16/20");
            editTextSubject3Percentage.setText("80.0");
            editTextSubject3MissMore.setText("4");
        }

        public void onRecalculateClick(View view) {
            // Recalculate attendance when the user clicks the button
            calculateAttendance();
            Toast.makeText(this, "Attendance recalculated", Toast.LENGTH_SHORT).show();
        }

        private void calculateAttendance() {
            // Replace the sample data with user input or actual attendance data

            // Subject 1
            String attendanceSubject1 = editTextSubject1Attendance.getText().toString();
            double percentageSubject1 = calculatePercentage(attendanceSubject1);
            int missMoreSubject1 = calculateMissMore(attendanceSubject1, percentageSubject1);

            // Subject 2
            String attendanceSubject2 = editTextSubject2Attendance.getText().toString();
            double percentageSubject2 = calculatePercentage(attendanceSubject2);
            int missMoreSubject2 = calculateMissMore(attendanceSubject2, percentageSubject2);

            // Subject 3
            String attendanceSubject3 = editTextSubject3Attendance.getText().toString();
            double percentageSubject3 = calculatePercentage(attendanceSubject3);
            int missMoreSubject3 = calculateMissMore(attendanceSubject3, percentageSubject3);

            // Update EditTexts with calculated values
            editTextSubject1Percentage.setText(String.format("%.1f", percentageSubject1));
            editTextSubject2Percentage.setText(String.format("%.1f", percentageSubject2));
            editTextSubject3Percentage.setText(String.format("%.1f", percentageSubject3));

            editTextSubject1MissMore.setText(String.valueOf(missMoreSubject1));
            editTextSubject2MissMore.setText(String.valueOf(missMoreSubject2));
            editTextSubject3MissMore.setText(String.valueOf(missMoreSubject3));
        }

        private double calculatePercentage(String attendance) {
            String[] parts = attendance.split("/");
            if (parts.length == 2) {
                int attended = Integer.parseInt(parts[0]);
                int total = Integer.parseInt(parts[1]);
                return (attended * 100.0) / total;
            }
            return 0.0;
        }

        private int calculateMissMore(String attendance, double percentage) {
            String[] parts = attendance.split("/");
            if (parts.length == 2) {
                int total = Integer.parseInt(parts[1]);
                return (int) Math.ceil((percentage * 80 / 100 - total + Integer.parseInt(parts[0])) / 0.8);
            }
            return 0;
        }
    }

