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
import com.example.sagar.moviedb1.model.Tv;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by SAGAR on 09-04-2018.
 */

public class TvRecyclerAdapter extends RecyclerView.Adapter<TvRecyclerAdapter.TvViewHolder>
{
    public interface onItemClickListener
    {
        void onClick(int position);
    }
    ArrayList<Tv> series;
    Context context;
    TvRecyclerAdapter.onItemClickListener listener;
    public TvRecyclerAdapter(Context context, ArrayList<Tv> series, TvRecyclerAdapter.onItemClickListener listener)
    {
        this.series=series;
        this.context=context;
        this.listener = listener;
    }
    @Override
    public TvRecyclerAdapter.TvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.row_movie,parent,false);
        TvRecyclerAdapter.TvViewHolder viewHolder=new TvRecyclerAdapter.TvViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final TvRecyclerAdapter.TvViewHolder holder, int position)
    {
        Tv movie=series.get(position);
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
        return series.size();
    }

    class TvViewHolder extends RecyclerView.ViewHolder
    {
        View itemView;
        ImageView poster;
        TextView vote_average;
        TextView title;
        TextView overview;
        public TvViewHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;
            poster=itemView.findViewById(R.id.poster);
            vote_average=itemView.findViewById(R.id.vote_average);
            title=itemView.findViewById(R.id.title);
            overview =itemView.findViewById(R.id.overview);
        }
    }
}
