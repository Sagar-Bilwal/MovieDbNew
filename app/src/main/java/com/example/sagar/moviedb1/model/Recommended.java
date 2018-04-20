package com.example.sagar.moviedb1.model;

/**
 * Created by SAGAR on 21-04-2018.
 */

public class Recommended
{
    private String title;
    private String poster_path;

    public Recommended(String title, String poster_path) {
        this.title = title;
        this.poster_path = poster_path;
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
}
