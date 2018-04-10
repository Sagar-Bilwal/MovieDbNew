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
import com.example.sagar.moviedb1.responses.MovieResponse;

import java.util.ArrayList;

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
    public String MOVIE_ID="MOVIE_ID";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.popular_movies, container, false);
        recyclerView=view.findViewById(R.id.recyclerView);
        movieRecyclerAdapter=new MovieRecyclerAdapter(view.getContext(),Movies,this);
        progressBar=view.findViewById(R.id.progressbar);
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
        Call<MovieResponse> call=ApiClient.getInstance().getMovieDbAPI().getMovies() ;
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                MovieResponse movies=response.body();
                progressBar.setVisibility(View.GONE);
                if(movies!=null) {
                    Movies.clear();
                    Movies.addAll(movies.getResults());
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
        int movie_id=Movies.get(position).getId();
        Toast.makeText(view.getContext(),(movie_id+""),Toast.LENGTH_LONG).show();
        Intent intent= new Intent(view.getContext(),ReviewActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt(MOVIE_ID,movie_id);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
