package com.example.college_companion;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference reference;
    ImageView pfp;
    TextView username,sapid,email,phone;


    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        reference= FirebaseDatabase.getInstance().getReference("users");
        pfp=findViewById(R.id.profilePicture);
        username=findViewById(R.id.username);
        email=findViewById(R.id.emailValue);
        phone=findViewById(R.id.contactNumberValue);
        sapid=findViewById(R.id.sapIdValue);
        user= FirebaseAuth.getInstance().getCurrentUser();
        userID=user.getUid();
        reference.child(userID).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                RegisterUser reguser=snapshot.getValue(RegisterUser.class);

                if (reguser!=null)
                {
                    String FNAME= reguser.fname;
                    String LNAME= reguser.lname;
                    String NUMBER= reguser.phone;
                    String EMAIL=reguser.email;
                    String SAP=reguser.sap;
                    String pa=reguser.downloadURl;
                    username.setText(FNAME+" "+LNAME);
                    phone.setText(NUMBER);
                    email.setText(EMAIL);
                    sapid.setText(SAP);

                    Glide.with(getApplicationContext()).load(pa).centerCrop().into(pfp);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(profile.this, "error", Toast.LENGTH_SHORT).show();
            }
        });

        Button backButton = findViewById(R.id.bt1);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, dashboardActivity.class);
                startActivity(intent);
            }
        });
    }
}