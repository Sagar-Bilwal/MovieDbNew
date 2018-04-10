package com.example.sagar.moviedb1.responses;

import com.example.sagar.moviedb1.model.Review;
import com.example.sagar.moviedb1.model.Tv;

import java.util.ArrayList;

/**
 * Created by SAGAR on 09-04-2018.
 */

public class TvResponse
{
    private ArrayList<Tv> results;

    public ArrayList<Tv> getResults() {
        return results;
    }

    public void setResults(ArrayList<Tv> results) {
        this.results = results;
    }
}
