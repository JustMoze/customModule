package com.reactlibrary;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import java.util.List;

import static java.security.AccessController.getContext;

public class DataCardRecyclerAdapter extends RecyclerView.Adapter<DataCardRecyclerAdapter.ViewHolder> {
    private List<Data> mMovies;
    ImageView imageView;
    ReactContext reactContext;
    private static ClickListener clickListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView myImageView;
        TextView myRatingView;
        TextView myGenreView;

        public ViewHolder(View itemView) {
            super(itemView);
            myImageView = itemView.findViewById(R.id.card_img);
            myRatingView = itemView.findViewById(R.id.rating);
            myGenreView = itemView.findViewById(R.id.genre);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            v.startAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.animation));
            WritableMap event = Arguments.createMap();
            event.putInt("position", getAdapterPosition());
            reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(getAdapterPosition(), "onPress", event);
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public DataCardRecyclerAdapter(List<Data> movies, ThemedReactContext context) {
        mMovies = movies;
        reactContext = context;
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        DataCardRecyclerAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View movieView = inflater.inflate(R.layout.card_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(movieView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DataCardRecyclerAdapter.ViewHolder holder, int position) {
        Data movie = mMovies.get(position);
        ImageView image = holder.myImageView;
        new LoadImageDrawable(image).execute(movie.getImageUrl());
        TextView ratingView = holder.myRatingView;
        TextView genreView = holder.myGenreView;
        ratingView.setText(String.valueOf(movie.getRating()));
        genreView.setText(String.valueOf(movie.getGenre()));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }
}