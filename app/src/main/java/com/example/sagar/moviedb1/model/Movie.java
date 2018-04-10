package com.example.sagar.moviedb1.model;

/**
 * Created by SAGAR on 08-04-2018.
 */

public class Movie
{
    private double vote_average;
    private String title;
    private String poster_path;
    private String overview;
    private int id;

    public Movie(int vote_average, String title, String poster_path, String overview , int id) {
        this.vote_average = vote_average;
        this.title = title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.id = id;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

