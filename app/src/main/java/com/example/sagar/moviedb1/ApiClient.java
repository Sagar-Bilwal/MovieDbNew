package com.example.sagar.moviedb1;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SAGAR on 08-04-2018.
 */

public class ApiClient
{
    private static ApiClient INSTANCE;
    private MovieDbAPI movieDbAPI;
    private ApiClient()
    {
        Retrofit retrofit= new Retrofit.Builder().baseUrl("http://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        movieDbAPI = retrofit.create(MovieDbAPI.class);
    }
    public static ApiClient getInstance() {
        if(INSTANCE == null){
            INSTANCE = new ApiClient();
        }
        return INSTANCE;
    }

    public MovieDbAPI getMovieDbAPI() {
        return movieDbAPI;
    }
}
