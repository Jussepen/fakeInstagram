package com.example.fakeinstagram.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("User")
public class User extends ParseObject {

    public static final String KEY_USERNAME = "username";

    public String getKeyUsername(){
        return getString(KEY_USERNAME);
    }
    public void setKeyUsername(String username){
        put(KEY_USERNAME, username);
    }






}
