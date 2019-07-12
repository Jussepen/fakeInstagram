package com.example.fakeinstagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.fakeinstagram.model.Post;
import com.parse.ParseImageView;

public class otherUser extends AppCompatActivity {

    Post post;
    ParseImageView ivProfileImage;
    TextView tvUsername;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_user);

        ivProfileImage = findViewById(R.id.ivProfileImage);
        tvUsername = findViewById(R.id.tvUsername);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                post = null;
            } else {
                post = (Post) extras.get("post");
            }
        } else {
            post = (Post) savedInstanceState.getSerializable("post");
        }


        ivProfileImage.setParseFile(post.getUser().getParseFile("profilePicture"));
        ivProfileImage.loadInBackground();
        tvUsername.setText(post.getUser().getUsername());

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.action_home:
                        intent = new Intent(otherUser.this, FeedActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.action_compose:
                        intent = new Intent(otherUser.this, HomeActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.action_profile:
                        intent = new Intent(otherUser.this, ProfileActivity.class);
                        startActivity(intent);
                        return true;
                    default: return true;
                }
            }
        });

    }


}
