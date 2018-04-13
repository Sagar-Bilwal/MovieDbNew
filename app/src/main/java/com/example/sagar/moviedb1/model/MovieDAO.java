package com.example.sagar.moviedb1.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SAGAR on 13-04-2018.
 */
@Dao
public interface
MovieDAO
{
    @Insert
    void insertMovie(Movie movie);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovies(ArrayList<Movie> movies);
    @Query("SELECT * FROM Movie")
    List<Movie> getAllMovies();
}
