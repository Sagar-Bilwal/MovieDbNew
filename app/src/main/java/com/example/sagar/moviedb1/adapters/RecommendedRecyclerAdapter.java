package com.example.sagar.moviedb1.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sagar.moviedb1.R;
import com.example.sagar.moviedb1.model.Recommended;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by SAGAR on 21-04-2018.
 */

public class RecommendedRecyclerAdapter extends RecyclerView.Adapter<RecommendedRecyclerAdapter.RecommendedViewHolder>
{
    ArrayList<Recommended> recommends;
    Context context;
    public RecommendedRecyclerAdapter(ArrayList<Recommended> recommends,Context context)
    {
        this.recommends = recommends;
        this.context = context;
    }
    @Override
    public RecommendedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        View view=layoutInflater.inflate(R.layout.recommended_review_movie,parent,false);
        RecommendedViewHolder viewHolder=new RecommendedViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecommendedRecyclerAdapter.RecommendedViewHolder holder, int position)
    {
        Recommended recommended=recommends.get(position);
        holder.moiveName.setText(recommended.getTitle());
        Picasso.get().load("http://image.tmdb.org/t/p/w342"+recommended.getPoster_path()).into(holder.moviePoster);
    }

    @Override
    public int getItemCount() {
        return recommends.size();
    }

    public class RecommendedViewHolder extends RecyclerView.ViewHolder
    {
        View itemView;
        ImageView moviePoster;
        TextView moiveName;
        public RecommendedViewHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;
            moiveName=itemView.findViewById(R.id.recommended_movie_name);
            moviePoster=itemView.findViewById(R.id.recommended_movie_image);
        }
    }
}
