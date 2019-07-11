package com.example.fakeinstagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {


    private EditText etGetUsername;
    private EditText etGetPassword;
    private EditText etGetEmail;
    private Button btnFinishSignup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etGetUsername = findViewById(R.id.etGetUsername);
        etGetPassword = findViewById(R.id.etGetUsername);
        etGetEmail = findViewById(R.id.etGetEmail);
        btnFinishSignup = findViewById(R.id.btnFinishSignup);

        btnFinishSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newUser();
            }
        });

    }

    void newUser (){
        // Create the ParseUser
        ParseUser user = new ParseUser();
        // Set core properties

        String newUsername = etGetUsername.getText().toString();
        String newPassword = etGetPassword.getText().toString();
        String newEmail = etGetEmail.getText().toString();


        user.setUsername(newUsername);
        user.setPassword(newPassword);
        user.setEmail(newEmail);
        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(com.parse.ParseException e) {
                    if (e == null ){
                        Log.d("SignupActivity", "New user created");
                        Intent intent = new Intent(SignupActivity.this, FeedActivity.class);
                        startActivity(intent);
                    }else {
                        Log.d("SignupActivity", "New user failed");

                    }
            }
        });

    }




}
