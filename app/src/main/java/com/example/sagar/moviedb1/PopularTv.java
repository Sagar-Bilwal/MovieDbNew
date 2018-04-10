package com.example.sagar.moviedb1;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sagar.moviedb1.adapters.MovieRecyclerAdapter;
import com.example.sagar.moviedb1.adapters.TvRecyclerAdapter;
import com.example.sagar.moviedb1.model.Movie;
import com.example.sagar.moviedb1.model.Tv;
import com.example.sagar.moviedb1.responses.MovieResponse;
import com.example.sagar.moviedb1.responses.TvResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularTv extends Fragment implements TvRecyclerAdapter.onItemClickListener {

    View view;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    TvRecyclerAdapter movieRecyclerAdapter;
    ArrayList<Tv> Movies = new ArrayList<>();
    public String MOVIE_ID="MOVIE_ID";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.activity_popular_tv, container, false);
        recyclerView=view.findViewById(R.id.recyclerViewTv);
        movieRecyclerAdapter=new TvRecyclerAdapter(view.getContext(),Movies,this);
        progressBar=view.findViewById(R.id.progressbarTv);
        fetchMovies();
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(movieRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }
    private void fetchMovies()
    {
        Call<TvResponse> call=ApiClient.getInstance().getMovieDbAPI().getTv();
        call.enqueue(new Callback<TvResponse>() {
            @Override
            public void onResponse(@NonNull Call<TvResponse> call, @NonNull Response<TvResponse> response) {
                TvResponse movies=response.body();
                progressBar.setVisibility(View.GONE);
                if(movies!=null) {
                    Movies.clear();
                    Movies.addAll(movies.getResults());
                    movieRecyclerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<TvResponse> call, @NonNull Throwable t) {
                Log.d("error", t.getMessage());
                progressBar.setVisibility(View.GONE);
                Toast.makeText(view.getContext(),"No Connection",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(int position) {

    }

}
