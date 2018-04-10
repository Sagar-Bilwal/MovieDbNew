package com.example.sagar.moviedb1.responses;

import com.example.sagar.moviedb1.model.Review;

import java.util.ArrayList;

/**
 * Created by SAGAR on 08-04-2018.
 */

public class ReviewResponse
{
    private ArrayList<Review> results;

    public ArrayList<Review> getResults() {
        return results;
    }

    public void setResults(ArrayList<Review> results) {
        this.results = results;
    }
}
