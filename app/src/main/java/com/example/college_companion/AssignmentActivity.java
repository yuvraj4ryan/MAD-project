package com.example.college_companion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AssignmentActivity extends AppCompatActivity {

    private RadioGroup subject1RadioGroup;
    private RadioGroup subject2RadioGroup;
    private RadioGroup subject3RadioGroup;
    private RadioGroup subject4RadioGroup;
    private RadioGroup subject5RadioGroup;
    private Button trackAssignmentsButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);

        subject1RadioGroup = findViewById(R.id.subject1RadioGroup);
        subject2RadioGroup = findViewById(R.id.subject2RadioGroup);
        subject3RadioGroup = findViewById(R.id.subject3RadioGroup);
        subject4RadioGroup = findViewById(R.id.subject4RadioGroup);
        subject5RadioGroup = findViewById(R.id.subject5RadioGroup);
        trackAssignmentsButton = findViewById(R.id.trackAssignmentsButton);
        backButton = findViewById(R.id.bt1);

        Button trackAssignmentsButton = findViewById(R.id.trackAssignmentsButton);
        trackAssignmentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show toast indicating that assignments are uploaded
                Toast.makeText(AssignmentActivity.this, "Assignments are uploaded", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setRadioGroupListeners() {
        RadioGroup.OnCheckedChangeListener listener = (group, checkedId) -> {
            // Check if all RadioButtons are checked
            boolean allYesChecked = isAllYesChecked();
            // Enable/disable the button accordingly
            trackAssignmentsButton.setEnabled(allYesChecked);
        };

        subject1RadioGroup.setOnCheckedChangeListener(listener);
        subject2RadioGroup.setOnCheckedChangeListener(listener);
        subject3RadioGroup.setOnCheckedChangeListener(listener);
        subject4RadioGroup.setOnCheckedChangeListener(listener);
        subject5RadioGroup.setOnCheckedChangeListener(listener);
    }

    private boolean isAllYesChecked() {
        return isAllYesCheckedInGroup(subject1RadioGroup) &&
                isAllYesCheckedInGroup(subject2RadioGroup) &&
                isAllYesCheckedInGroup(subject3RadioGroup) &&
                isAllYesCheckedInGroup(subject4RadioGroup) &&
                isAllYesCheckedInGroup(subject5RadioGroup);
    }

    private boolean isAllYesCheckedInGroup(RadioGroup radioGroup) {
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        if (checkedRadioButtonId != -1) {
            RadioButton radioButton = findViewById(checkedRadioButtonId);
            return radioButton.getText().equals("Yes");
        }
        return false;
    }

    public void onSharePDF(View view) {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("android.resource://com.example.college_companion/raw/filename.docx"));
        startActivity(Intent.createChooser(intent, ""));
    }

    public void onTrackAssignments(View view) {
        if (isAllYesChecked()) {
            // Code to execute when all assignments are marked as 'Yes'
            // For example, navigating to another activity
            Toast.makeText(this, "Navigating to Weekly Assignments", Toast.LENGTH_SHORT).show();
        } else {
            // Show toast indicating deadlines are not met
            Toast.makeText(this, "Deadlines are not met", Toast.LENGTH_SHORT).show();
        }
    }


}