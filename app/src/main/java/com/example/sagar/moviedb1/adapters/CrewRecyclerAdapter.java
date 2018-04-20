package com.example.sagar.moviedb1.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sagar.moviedb1.R;
import com.example.sagar.moviedb1.model.Crew;
import com.example.sagar.moviedb1.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by SAGAR on 21-04-2018.
 */

public class CrewRecyclerAdapter extends RecyclerView.Adapter<CrewRecyclerAdapter.CrewViewHolder>
{
    ArrayList<Crew> crews;
    Context context;
    public CrewRecyclerAdapter(ArrayList<Crew> crews,Context context)
    {
        this.crews = crews;
        this.context = context;
    }
    @Override
    public CrewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        View view=layoutInflater.inflate(R.layout.content_review,parent,false);
        CrewViewHolder viewHolder=new CrewViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CrewRecyclerAdapter.CrewViewHolder holder, int position)
    {
        Crew crew=crews.get(position);
        holder.crew_name.setText(crew.getCrew_name());
        Picasso.get().load("http://image.tmdb.org/t/p/w342"+crew.getCrew_profile_path()).into(holder.crew_poster);
    }

    @Override
    public int getItemCount() {
        return crews.size();
    }


    public class CrewViewHolder extends RecyclerView.ViewHolder {

        View view;
        ImageView crew_poster;
        TextView crew_name;

        public CrewViewHolder(View view)
        {
            super(view);
            this.view = view;
            crew_poster=view.findViewById(R.id.crew_image);
            crew_name=view.findViewById(R.id.crew_name);
        }
    }
}
