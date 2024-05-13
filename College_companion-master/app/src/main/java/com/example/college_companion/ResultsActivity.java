package com.example.college_companion;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultsActivity extends AppCompatActivity {

    private EditText editTextDesiredGrade;
    private TextView subject1Marks, subject2Marks, subject3Marks;
    private TextView textViewGPA, textViewMarksLost, textViewMarksRequired;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        editTextDesiredGrade = findViewById(R.id.editTextDesiredGrade);
        subject1Marks = findViewById(R.id.subject1Marks);
        subject2Marks = findViewById(R.id.subject2Marks);
        subject3Marks = findViewById(R.id.subject3Marks);
        textViewGPA = findViewById(R.id.textViewGPA);
        textViewMarksLost = findViewById(R.id.textViewMarksLost);
        textViewMarksRequired = findViewById(R.id.textViewMarksRequired);

        Button buttonCalculate = findViewById(R.id.buttonCalculate);
        Button buttonBack = findViewById(R.id.buttonBack);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double desiredGrade = Double.parseDouble(editTextDesiredGrade.getText().toString());


                int marksSubject1 = 8;
                int marksSubject2 = 9;
                int marksSubject3 = 6;


                subject1Marks.setText(String.format("Subject 1: %d/10", marksSubject1));
                subject2Marks.setText(String.format("Subject 2: %d/10", marksSubject2));
                subject3Marks.setText(String.format("Subject 3: %d/10", marksSubject3));


                int totalMarks = marksSubject1 + marksSubject2 + marksSubject3;
                double gpa = calculateGPA(totalMarks);
                textViewGPA.setText(String.format("Calculated GPA: %.2f", gpa));


                int perfectScore = 30;
                int marksLost = perfectScore - totalMarks;
                textViewMarksLost.setText(String.format("Marks Lost Overall: %d", marksLost));


                double goalGPA = desiredGrade;
                int marksRequired = calculateMarksRequired(goalGPA, totalMarks);
                textViewMarksRequired.setText(String.format("Marks Required for Goal GPA: %d", marksRequired));
            }
        });


        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    private double calculateGPA(int totalMarks) {

        return (totalMarks / 30.0) * 4.0;
    }

    private int calculateMarksRequired(double goalGPA, int currentMarks) {

        return (int) Math.ceil((goalGPA / 4.0) * 30) - currentMarks;
}
}
