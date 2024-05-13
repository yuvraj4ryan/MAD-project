package com.example.college_companion;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class dashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialize the GridView
        GridView gridView = findViewById(R.id.imageButtonsGridView);

        // List of image resource IDs for the ImageButtons
        List<Integer> imageButtonIds = new ArrayList<>();
        imageButtonIds.add(R.drawable.assignments);
        imageButtonIds.add(R.drawable.attendance);
        imageButtonIds.add(R.drawable.results);
        imageButtonIds.add(R.drawable.timetable);

        // List of labels for the ImageButtons
        List<String> labels = new ArrayList<>();
        labels.add("Assignments");
        labels.add("Attendance");
        labels.add("Results");
        labels.add("Timetable");

        // Set up adapter for the GridView
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, imageButtonIds) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LinearLayout layout = new LinearLayout(getContext());
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setGravity(Gravity.CENTER);

                ImageButton imageButton = new ImageButton(getContext());
                imageButton.setLayoutParams(new LinearLayout.LayoutParams(450, 450));
                imageButton.setScaleType(ImageButton.ScaleType.FIT_CENTER);
                imageButton.setPadding(8, 8, 8, 8);
                imageButton.setImageResource(getItem(position));
                imageButton.setContentDescription(getResources().getResourceEntryName(getItem(position)));

                TextView textView = new TextView(getContext());
                textView.setText(labels.get(position));
                textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                textView.setGravity(Gravity.CENTER);

                // Set text color to black and remove background color
                textView.setTextColor(Color.BLACK);
                textView.setBackgroundColor(Color.TRANSPARENT);

                // Increase text size and make it bold
                textView.setTextSize(18);
                textView.setTypeface(null, Typeface.BOLD);

                layout.addView(imageButton);
                layout.addView(textView);

                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (position) {
                            case 0:
                                Intent intent0 = new Intent(dashboardActivity.this, AssignmentActivity.class);
                                startActivity(intent0);
                                break;
                            case 1:
                                Intent intent1 = new Intent(dashboardActivity.this, AttendanceActivity.class);
                                startActivity(intent1);
                                break;
                            case 2:
                                Intent intent2 = new Intent(dashboardActivity.this, ResultsActivity.class);
                                startActivity(intent2);
                                break;
                            case 3:
                                Intent intent3 = new Intent(dashboardActivity.this, TimetableActivity.class);
                                startActivity(intent3);
                                break;
                        }
                    }
                });

                return layout;
            }
        };

        gridView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            Intent intent = new Intent(dashboardActivity.this, loginactivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.action_profile) {
            Intent intent = new Intent(dashboardActivity.this, profile.class);
            startActivity(intent);
            return true;
        }
        return false;
    }
}