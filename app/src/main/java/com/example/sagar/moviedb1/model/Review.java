package com.example.sagar.moviedb1.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SAGAR on 08-04-2018.
 */

public class Review
{
    private String key;

    public Review(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
