package com.example.sagar.moviedb1;

import com.example.sagar.moviedb1.model.Tv;
import com.example.sagar.moviedb1.responses.CrewResponse;
import com.example.sagar.moviedb1.responses.MovieResponse;
import com.example.sagar.moviedb1.responses.ReviewResponse;
import com.example.sagar.moviedb1.responses.TvResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by SAGAR on 08-04-2018.
 */

public interface MovieDbAPI
{
    final String API_Key="d7736b944015d9ad77241f6761abe09a";
    @GET("/3/movie/popular?api_key=d7736b944015d9ad77241f6761abe09a")
    Call<MovieResponse> getMovies();
    @GET("/3/movie/{MOVIE_ID}/videos?api_key=d7736b944015d9ad77241f6761abe09a")
    Call<ReviewResponse> getReviews(@Path("MOVIE_ID") String MOVIE_ID);
    @GET("/3/tv/popular?api_key=d7736b944015d9ad77241f6761abe09a")
    Call<TvResponse> getTv();
    @GET("/3/movie/{MOVIE_ID}/credits?api_key=d7736b944015d9ad77241f6761abe09a")
    Call<CrewResponse> getCrew(@Path("MOVIE_ID") String MOVIE_ID);
}
