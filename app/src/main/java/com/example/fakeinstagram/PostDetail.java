package com.example.fakeinstagram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.widget.TextView;

import com.example.fakeinstagram.model.Post;
import com.parse.ParseImageView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class PostDetail extends AppCompatActivity {

    Post post;
    ParseImageView ivImagePost;
    TextView tvDescription;
    TextView tvTimeStamp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);


        ivImagePost = findViewById(R.id.ivImagePost);
        tvDescription = findViewById(R.id.tvDescription);
        tvTimeStamp = findViewById(R.id.tvTimeStamp);

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


        ivImagePost.setParseFile(post.getImage());
        ivImagePost.loadInBackground();
        tvDescription.setText(post.getKeyDescription());
        tvTimeStamp.setText( getRelativeTimeAgo(post.getCreatedAt().toString()));

    }


    public String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return relativeDate;
    }





}
