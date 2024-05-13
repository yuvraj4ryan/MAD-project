package com.example.college_companion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

public class ResultsActivity extends AppCompatActivity {

    EditText[] editTextICAMarks;
    EditText[] editTextTotalMarks;
    Button buttonCalculate;
    TextView textViewGPA;
    TextView textViewMarksLost;
    TextView textViewMarksRequired;
    EditText editTextDesiredGrade;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Initialize views
        editTextICAMarks = new EditText[]{
                findViewById(R.id.subject1ICA),
                findViewById(R.id.subject2ICA),
                findViewById(R.id.subject3ICA),
                findViewById(R.id.subject4ICA),
                findViewById(R.id.subject5ICA)
        };
        editTextTotalMarks = new EditText[]{
                findViewById(R.id.subject1PredictedMarks),
                findViewById(R.id.subject2PredictedMarks),
                findViewById(R.id.subject3PredictedMarks),
                findViewById(R.id.subject4PredictedMarks),
                findViewById(R.id.subject5PredictedMarks)
        };
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewGPA = findViewById(R.id.textViewGPA);
        textViewMarksLost = findViewById(R.id.textViewMarksLost);
        textViewMarksRequired = findViewById(R.id.textViewMarksRequired);
        editTextDesiredGrade = findViewById(R.id.editTextDesiredGrade);
        backButton = findViewById(R.id.buttonBack);

        // Set onClickListener for the calculate button
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResults();
            }
        });

        // Set OnClickListener for the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultsActivity.this, dashboardActivity.class);
                startActivity(intent);
            }
        });
    }

    private void calculateResults() {
        try {
            // Get the number of courses
            int numCourses = editTextICAMarks.length;

            // Arrays to store ICA marks and total marks for each course
            double[] icaMarks = new double[numCourses];
            double[] totalMarks = new double[numCourses];

            // Parse ICA marks and total marks
            for (int i = 0; i < numCourses; i++) {
                icaMarks[i] = Double.parseDouble(editTextICAMarks[i].getText().toString());
                totalMarks[i] = Double.parseDouble(editTextTotalMarks[i].getText().toString());

                // Validate marks within a reasonable range (adjust as needed)
                if (icaMarks[i] < 0 || icaMarks[i] > 50 || totalMarks[i] < 0 || totalMarks[i] > 100) {
                    throw new NumberFormatException("Marks must be between 0 and 50 for ICA and 0 and 100 for Total Marks.");
                }
            }

            // Calculate GPA, marks lost, and marks required
            double totalGPA = 0.0;
            double totalMarksLost = 0.0;
            double totalMarksRequired = 0.0;

            for (int i = 0; i < numCourses; i++) {
                double[] result = calculateSubjectResults(icaMarks[i], totalMarks[i]);
                totalGPA += result[0];
                totalMarksLost += result[1];
                totalMarksRequired += result[2];
            }

            // Calculate average GPA
            double averageGPA = totalGPA / numCourses;

            // Display GPA
            DecimalFormat df = new DecimalFormat("#.##");
            textViewGPA.setText("Calculated GPA: " + df.format(averageGPA));
            textViewMarksLost.setText("Marks Lost Overall: " + df.format(totalMarksLost));
            textViewMarksRequired.setText("Marks Required for Goal GPA: " + df.format(totalMarksRequired));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers for ICA marks and Total marks.", Toast.LENGTH_SHORT).show();
        }
    }

    private double[] calculateSubjectResults(double icaMarks, double totalMarks) {
        // Scale down ICA marks to be out of 50
        double scaledICAMarks = icaMarks / 50;

        // Scale down total marks to be out of 50
        double scaledTotalMarks = totalMarks / 100;

        // Calculate the weighted average of ICA and total marks
        double weightedAverage = (scaledICAMarks * 0.5) + (scaledTotalMarks * 0.5);

        // Calculate GPA (out of 4)
        double gpa = weightedAverage * 4;

        // Calculate marks lost (difference between actual and predicted)
        double marksLost = (1 - weightedAverage) * 50;

        // Get the desired goal GPA
        double desiredGPA = Double.parseDouble(editTextDesiredGrade.getText().toString());

        // Calculate the marks required to achieve the goal GPA
        double marksRequired = ((desiredGPA / 4) - weightedAverage) * 50;

        return new double[]{gpa, marksLost, marksRequired};
    }
}