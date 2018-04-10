package com.example.sagar.moviedb1.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sagar.moviedb1.R;
import com.example.sagar.moviedb1.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by SAGAR on 08-04-2018.
 */

public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.MovieViewHolder>
{
    public interface onItemClickListener
    {
        void onClick(int position);
    }
    ArrayList<Movie> movies;
    Context context;
    onItemClickListener listener;
    public MovieRecyclerAdapter(Context context,ArrayList<Movie> movies,onItemClickListener listener)
    {
        this.movies=movies;
        this.context=context;
        this.listener = listener;
    }
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.row_movie,parent,false);
        MovieViewHolder viewHolder=new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MovieRecyclerAdapter.MovieViewHolder holder, int position)
    {
        Movie movie=movies.get(position);
        holder.overview.setText(movie.getOverview());
        holder.title.setText(movie.getTitle());
        holder.vote_average.setText(movie.getVote_average()+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(holder.getAdapterPosition());
            }
        });
        Picasso.get().load("http://image.tmdb.org/t/p/w780"+movie.getPoster_path()).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder
    {
        View itemView;
        ImageView poster;
        TextView vote_average;
        TextView title;
        TextView overview;
        public MovieViewHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;
            poster=itemView.findViewById(R.id.poster);
            vote_average=itemView.findViewById(R.id.vote_average);
            title=itemView.findViewById(R.id.title);
            overview =itemView.findViewById(R.id.overview);
        }
    }
}

