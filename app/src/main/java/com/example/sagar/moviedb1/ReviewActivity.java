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

import com.example.sagar.moviedb1.adapters.CrewRecyclerAdapter;
import com.example.sagar.moviedb1.adapters.RecommendedRecyclerAdapter;
import com.example.sagar.moviedb1.adapters.ReviewRecyclerAdapter;
import com.example.sagar.moviedb1.model.Config;
import com.example.sagar.moviedb1.model.Crew;
import com.example.sagar.moviedb1.model.Recommended;
import com.example.sagar.moviedb1.model.Review;
import com.example.sagar.moviedb1.responses.CrewResponse;
import com.example.sagar.moviedb1.responses.RecommendedResponse;
import com.example.sagar.moviedb1.responses.ReviewResponse;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final int RECOVERY_REQUEST = 1;
    CrewRecyclerAdapter crewRecyclerAdapter;
    RecommendedRecyclerAdapter recommendedRecyclerAdapter;


    ArrayList<Crew> Crews=new ArrayList<>();
    ArrayList<Recommended> Recommends=new ArrayList<>();
    private YouTubePlayerView youTubeView;
    RecyclerView recyclerView;
    RecyclerView recommendedRecyclerView;
    Intent intent;
    Bundle bundle;
    String movieId;
    private YouTubePlayer youTubePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        intent =getIntent();
        crewRecyclerAdapter=new CrewRecyclerAdapter(Crews,this);
        recommendedRecyclerAdapter=new RecommendedRecyclerAdapter(Recommends,this);
        bundle=intent.getExtras();
        assert bundle != null;
        movieId=bundle.getString("MOVIE_ID","");


        fetchCrews();


        recyclerView=findViewById(R.id.crew_recyclerView);
        recyclerView.setAdapter(crewRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        fetchRecommendations();

        recommendedRecyclerView=findViewById(R.id.recommended_recyclerView);
        recommendedRecyclerView.setAdapter(recommendedRecyclerAdapter);
        recommendedRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recommendedRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recommendedRecyclerView.setItemAnimator(new DefaultItemAnimator());


        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(Config.YOUTUBE_API_KEY, this);
    }

    public void fetchCrews()
    {
        Call<CrewResponse> call=ApiClient.getInstance().getMovieDbAPI().getCrew(movieId) ;
        call.enqueue(new Callback<CrewResponse>() {
            @Override
            public void onResponse(@NonNull Call<CrewResponse> call, @NonNull Response<CrewResponse> response) {
                CrewResponse crews=response.body();
                if(crews!=null) {
                    Crews.clear();
                    Crews.addAll(crews.getCast());
                    crewRecyclerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<CrewResponse> call, @NonNull Throwable t) {
                Log.d("error", t.getMessage());
                Toast.makeText(ReviewActivity.this,"No Connection",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void fetchRecommendations()
    {
        Call<RecommendedResponse> call=ApiClient.getInstance().getMovieDbAPI().getRecommended(movieId) ;
        call.enqueue(new Callback<RecommendedResponse>() {
            @Override
            public void onResponse(@NonNull Call<RecommendedResponse> call, @NonNull Response<RecommendedResponse> response) {
                RecommendedResponse recommends=response.body();
                if(recommends!=null) {
                    Recommends.clear();
                    Recommends.addAll(recommends.getResults());
                    recommendedRecyclerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<RecommendedResponse> call, @NonNull Throwable t) {
                Log.d("error", t.getMessage());
                Toast.makeText(ReviewActivity.this,"No Connection",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {
            if(youTubePlayer==null)
            {
                youTubePlayer=player;
            }
            youTubePlayer.loadVideo(movieId);
            youTubePlayer.play();
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            String error = String.format(getString(R.string.player_error), errorReason.toString());
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.YOUTUBE_API_KEY, this);
        }
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return youTubeView;
    }
}

