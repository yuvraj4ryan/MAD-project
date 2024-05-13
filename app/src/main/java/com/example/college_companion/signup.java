package com.example.college_companion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class signup extends AppCompatActivity {
    ImageView pfppic,pfppicfalseback;
    ImageButton pfpbut;
    MediaPlayer musicsucess,musicfail;
    Spinner subject,agee,gender,yearo;
    EditText Fname,Lname,Email,Phone,Password,Password2,Sapid;
    Button subs,retrun;
    ProgressBar progressBar;
    RelativeLayout getRelativeLayout2;
    Integer a=0;
    //to make user
    FirebaseAuth mAuth;
    DatabaseReference userRef;
    FirebaseDatabase database;
    FirebaseUser firebaseUser;
    RelativeLayout relativeLayout1;
    String userid;
    String email,password,password2,fname,lname,phone,ages,sap;

    //to upload pic
    private FirebaseStorage store;
    private StorageReference storageReference=FirebaseStorage.getInstance().getReference();
    private Bitmap bitmap;//looks useless but is used at bottom
    private Uri uri;
    String DownloadURl;

    private final int req= 12;//any val

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        store= FirebaseStorage.getInstance();
        storageReference=store.getReference();
        database=FirebaseDatabase.getInstance();
        userRef = FirebaseDatabase.getInstance().getReference().child("Register Users");

        getRelativeLayout2=findViewById(R.id.reletive2);
        mAuth = FirebaseAuth.getInstance();
        userid=mAuth.getUid();

        Fname=findViewById(R.id.fname);
        Lname=findViewById(R.id.lname);
        Email=findViewById(R.id.email);
        Phone=findViewById(R.id.phone);
        Password=findViewById(R.id.pass);
        Password2=findViewById(R.id.pass2);
        Sapid=findViewById(R.id.SAP);

        retrun=findViewById(R.id.button2log);

        retrun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(signup.this,loginactivity.class);
                startActivity(intent);
                finish();
            }
        });

        //pfp assignments
        pfppic=findViewById(R.id.pfp);//imageview
        pfpbut=findViewById(R.id.pfpb);//image button transparent
        pfppicfalseback=findViewById(R.id.pfpshow);


        pfpbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallry();
            }
        });


        subs=findViewById(R.id.buttonSignup2);
        subs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseUser = mAuth.getCurrentUser();

                fname = Fname.getText().toString().trim();
                lname = Lname.getText().toString().trim();
                email = Email.getText().toString().trim();
                password=Password.getText().toString().trim();
                password2=Password2.getText().toString().trim();
                phone=Phone.getText().toString().trim();
                sap=Sapid.getText().toString().trim();

                if (fname.equals("")||fname.contains(" "))
                {

                    Fname.setError("Enter First Name");
                    a=a+1;
                    return;
                }
                if (sap.equals("")||sap.contains(" ")||!isNumeric(sap))
                {
                    Sapid.setText("Please fill sap ID properly");
                    a=a+1;
                    return;
                }
                if (lname.equals("")||lname.contains(" "))
                {

                    Lname.setError("Enter Last Name");
                    a=a+1;
                    return;
                }

                if (email.equals("")||!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {

                    Email.setError("Enter Valid Email");
                    a=a+1;
                    return;
                }

                if (phone.equals("")||phone.contains(" ")||!isNumeric(phone))
                {


                    Phone.setError("Enter Phone Number");
                    a=a+1;
                    return;
                }
                else if (phone.length()<10)
                {
                    Phone.setError("10 digits");
                    a=a+1;
                    return;
                }

                if(password.equals("")||password.contains(" "))
                {

                    a=a+1;
                    Password.setError("Set Password");
                    return;
                }
                else if (password.length()<8)
                {

                    a=a+1;
                    Password.setError("8 digit or more");
                    return;
                }
                if(password2.equals("")||password2.contains(" ")||!password2.equals(password))
                {

                    a=a+1;
                   Password2.setError("ReEnter Correct Pass");

                    return;
                }

                if (pfppic.getDrawable()==null)
                {
                    musicfail.start();
                    a=a+1;
                    Toast.makeText(signup.this, "Select Image", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (a>=1)
                {
                    a=0;
                    Fname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View view, boolean b) {
                            Fname.setError(null);
                        }
                    });
                    Lname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View view, boolean b) {
                            Lname.setError(null);
                        }
                    });
                    Email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View view, boolean b) {
                            Email.setError(null);
                        }
                    });
                    Phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View view, boolean b) {
                            Phone.setError(null);
                        }
                    });
                    Password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View view, boolean b) {
                            Password.setError(null);
                        }
                    });
                    Password2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View view, boolean b) {
                            Password2.setError(null);
                        }
                    });
                }

                else if (a==0)
                {

                    subs.setEnabled(false);
                    uploadpfp();
                    String downloadURl=DownloadURl;
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                RegisterUser user= new RegisterUser(fname,lname,email,phone,ages,sap,downloadURl);
                                FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful())
                                        {
                                            Toast.makeText(signup.this, "Signup Success", Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {
                                            //checks if device is online
                                            ConnectivityManager conman =(ConnectivityManager)
                                                    getSystemService(Context.CONNECTIVITY_SERVICE);
                                            NetworkInfo networkInfo=conman.getActiveNetworkInfo();
                                            if (networkInfo!=null)
                                            {
                                                Toast.makeText(signup.this, "User not created try later", Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.INVISIBLE);
                                            }
                                            else
                                            {
                                                Toast.makeText(signup.this, "User not created You may be OFFLINE", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.INVISIBLE);
                                            }
                                        }
                                    }
                                });
                            }
                            else
                            {
                                Toast.makeText(signup.this, "Auth Problem"+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        }
                    });




                    Timer buttonTimer = new Timer();
                    buttonTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    subs.setEnabled(true);
                                    Toast.makeText(signup.this, "Submit Re enabled", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }, 10000);


                }
            }
        });


    }

    private boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
    private void uploadpfp()
    {//try not to touch this baron //touched sorry 1-june-2022 saved info of old in a doc uploadpfpfbyuri this way is better
        if (bitmap!=null)
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //0-100 accepted
            //most-least compressed
            bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos);
            byte[] finalimg= baos.toByteArray();
            final StorageReference filepath;

            filepath=storageReference.child("pfp").child(finalimg+"jpg"+" "+fname+" "+lname+" "+email);
            final UploadTask uploadTask=filepath.putBytes(finalimg);
            uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful())
                    {
                        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        DownloadURl=String.valueOf(uri);
                                        database.getReference().child("users").child(FirebaseAuth.getInstance().getUid()).child("downloadURl").setValue(uri.toString());

                                    }
                                });
                            }
                        });
                    }
                    else {
                        Toast.makeText(signup.this, "something went wrong with IMAGE", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }

    // handsoff below it works
    private void openGallry()
    {
        Intent pickImage=new Intent();
        pickImage.setType("image/*");
        pickImage.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(pickImage,req);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==req && resultCode==RESULT_OK && data!= null && data.getData()!=null){


            uri=data.getData();
            try {
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Glide.with(getApplicationContext()).load(data.getData()).centerCrop().into(pfppic);
        }
        else {
            Toast.makeText(this, "error in setting image", Toast.LENGTH_SHORT).show();
        }
    }

}