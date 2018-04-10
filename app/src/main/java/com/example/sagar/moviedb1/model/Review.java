package com.example.sagar.moviedb1.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SAGAR on 08-04-2018.
 */

public class Review
{
    private String author;
    @SerializedName("content")
    private String review;

    public Review(String author, String review) {
        this.author = author;
        this.review = review;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
