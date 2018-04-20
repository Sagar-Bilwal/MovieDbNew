package com.example.sagar.moviedb1;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sagar.moviedb1.adapters.MovieRecyclerAdapter;
import com.example.sagar.moviedb1.model.Movie;
import com.example.sagar.moviedb1.model.MovieDbDatabase;
import com.example.sagar.moviedb1.model.Review;
import com.example.sagar.moviedb1.responses.MovieResponse;
import com.example.sagar.moviedb1.responses.ReviewResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by SAGAR on 08-04-2018.
 */

public class PopularMovies extends Fragment implements MovieRecyclerAdapter.onItemClickListener {
    View view;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    MovieRecyclerAdapter movieRecyclerAdapter;
    ArrayList<Movie> Movies = new ArrayList<>();
    List<Movie> DbMovies=new ArrayList<>();
    public String MOVIE_ID="MOVIE_ID";
    int check=0;
    ArrayList<Review> Reviews=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.popular_movies, container, false);
        recyclerView=view.findViewById(R.id.recyclerView);
        movieRecyclerAdapter=new MovieRecyclerAdapter(view.getContext(), Movies,this);
        progressBar=view.findViewById(R.id.progressbar);
        MainActivity.check=0;
        fetchMovies();
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(movieRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }
    public void fetchMovies()
    {
        DbMovies=MovieDbDatabase.getINSTANCE(view.getContext()).getMovieDAO().getAllMovies();
        if(DbMovies!=null) {
            Movies.addAll(DbMovies);
            movieRecyclerAdapter.notifyDataSetChanged();
        }
        Call<MovieResponse> call=ApiClient.getInstance().getMovieDbAPI().getMovies() ;
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                MovieResponse movies=response.body();
                progressBar.setVisibility(View.GONE);
                if(movies!=null) {
                    Movies.clear();
                    Movies.addAll(movies.getResults());
                    MovieDbDatabase.getINSTANCE(view.getContext()).getMovieDAO().insertMovies(Movies);
                    movieRecyclerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                Log.d("error", t.getMessage());
                progressBar.setVisibility(View.GONE);
                Toast.makeText(view.getContext(),"No Connection",Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onClick(int position) {
        fetchReview(position);
        int movie_id=Movies.get(position).getId();
        Toast.makeText(view.getContext(),""+movie_id,Toast.LENGTH_LONG).show();
        Intent intent= new Intent(view.getContext(),ReviewActivity.class);
        Bundle bundle=new Bundle();
        if(Reviews.size()!=0) {
            if(check==1) {
                bundle.putString(MOVIE_ID, (movie_id+""));
                check=0;
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
        else
        {
            Toast.makeText(view.getContext(),"Click Again",Toast.LENGTH_LONG).show();
        }
    }
    private void fetchReview(int position)
    {
        int movie_id=Movies.get(position).getId();
        String urlMovieId=movie_id+"";
        Call<ReviewResponse> call=ApiClient.getInstance().getMovieDbAPI().getReviews(urlMovieId) ;
        call.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(@NonNull Call<ReviewResponse> call, @NonNull Response<ReviewResponse> response) {
                ReviewResponse reviews=response.body();
                if(reviews!=null) {
                    Reviews.clear();
                    check=1;
                    Reviews.addAll(reviews.getResults());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ReviewResponse> call, @NonNull Throwable t) {
                Log.d("error", t.getMessage());
                Toast.makeText(view.getContext(),"No Connection",Toast.LENGTH_LONG).show();
            }
        });
    }
}
