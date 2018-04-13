package com.example.sagar.moviedb1.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by SAGAR on 13-04-2018.
 */
@Database(entities = {Movie.class,Tv.class},version = 1)
public abstract class MovieDbDatabase extends RoomDatabase
{
    public static MovieDbDatabase INSTANCE;
    public static MovieDbDatabase getINSTANCE(Context context)
    {
        if(INSTANCE==null)
        {
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),MovieDbDatabase.class,"MOVIEDB").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
    public abstract MovieDAO getMovieDAO();
    public abstract TvDAO getTvDAO();
}
