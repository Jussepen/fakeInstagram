package com.example.fakeinstagram.model;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("User")
public class User extends ParseObject {

    public static final String KEY_USER = "objectId";
    public static final String KEY_PROFILEPICTURE = "profilePicture";

    public ParseFile getKeyProfilePicture(){
        return getParseFile(KEY_PROFILEPICTURE);
    }

    public void setKeyProfilePicture(ParseFile image){
        put(KEY_PROFILEPICTURE, image);
    }

    public ParseUser getUser(){
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user){
        put(KEY_USER, user);
    }


}
