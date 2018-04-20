package com.example.sagar.moviedb1.responses;

import com.example.sagar.moviedb1.model.Crew;

import java.util.ArrayList;

/**
 * Created by SAGAR on 21-04-2018.
 */

public class CrewResponse
{
    private ArrayList<Crew> cast;

    public ArrayList<Crew> getCast() {
        return cast;
    }

    public void setCast(ArrayList<Crew> results) {
        this.cast = results;
    }
}
