package com.example.sagar.moviedb1.responses;

import com.example.sagar.moviedb1.model.Recommended;

import java.util.ArrayList;

/**
 * Created by SAGAR on 21-04-2018.
 */

public class RecommendedResponse
{
    private ArrayList<Recommended> results;

    public ArrayList<Recommended> getResults() {
        return results;
    }

    public void setResults(ArrayList<Recommended> results) {
        this.results = results;
    }
}
