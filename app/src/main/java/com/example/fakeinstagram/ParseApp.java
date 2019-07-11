package com.example.fakeinstagram;

import android.app.Application;

import com.example.fakeinstagram.model.Post;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);



        final Parse.Configuration configuration = new Parse.Configuration.Builder(ParseApp.this)
                .applicationId("myAppId")
                .clientKey("myMasterKey")
                .server("http://jesus-fbuinstagram.herokuapp.com/parse")
                .build();

        Parse.initialize(configuration);
    }

}
