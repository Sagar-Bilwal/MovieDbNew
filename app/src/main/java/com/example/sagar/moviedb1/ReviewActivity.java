package com.example.sagar.moviedb1;

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
import android.view.View;
import android.widget.Toast;

import com.example.sagar.moviedb1.adapters.ReviewRecyclerAdapter;
import com.example.sagar.moviedb1.model.Review;
import com.example.sagar.moviedb1.responses.ReviewResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ReviewRecyclerAdapter reviewRecyclerAdapter;
    ArrayList<Review> Reviews=new ArrayList<>();
    Intent intent;
    Bundle bundle;
    int movieId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        intent =getIntent();
        bundle=intent.getExtras();
        assert bundle != null;
        movieId=bundle.getInt("MOVIE_ID",0);
        reviewRecyclerAdapter=new ReviewRecyclerAdapter(this,Reviews);
        recyclerView=findViewById(R.id.ReviewRecyclerView);
        fetchReview();
        recyclerView.setAdapter(reviewRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void fetchReview()
    {
        String urlMovieId=movieId+"";
        Call<ReviewResponse> call=ApiClient.getInstance().getMovieDbAPI().getReviews(urlMovieId) ;
        call.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(@NonNull Call<ReviewResponse> call, @NonNull Response<ReviewResponse> response) {
                ReviewResponse reviews=response.body();
                if(reviews!=null) {
                    Reviews.clear();
                    Reviews.addAll(reviews.getResults());
                    reviewRecyclerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ReviewResponse> call, @NonNull Throwable t) {
                Log.d("error", t.getMessage());
                Toast.makeText(ReviewActivity.this,"No Connection",Toast.LENGTH_LONG).show();
            }
        });
    }
}

