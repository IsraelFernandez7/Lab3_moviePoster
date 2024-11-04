package com.example.movieposter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Adapter class for RecyclerView to display movie posters.
 * This class is responsible for binding poster data to views and handling the selection of posters.
 */
public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterViewHolder>{
    @NonNull
    @Override

    /**
     * Inflates the layout for an individual item in the RecyclerView.
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new instance of PosterViewHolder holding the inflated item view.
     */
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PosterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_poster, parent, false));
    }


    /**
     * Binds the data from a Poster object to the view holder.
     * @param holder The ViewHolder which should be updated to represent the contents of the item at the given position.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {

        holder.bindPoster(posterList.get(position));
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return posterList.size();
    }

    private List<Poster>posterList;

    /**
     * Retrieves a list of posters that have been selected by the user.
     * @return A list of selected Poster objects.
     */
    public List<Poster> getSelectedPosters(){
        List<Poster> selectedPosters = new ArrayList<>();
        for (Poster poster : this.posterList){
            if(poster.isSelected){
                selectedPosters.add(poster);
            }
        }
        return selectedPosters;
    }

    /**
     * Constructs the PosterAdapter with a specified list of posters and a listener.
     * @param posterList the list of posters to be displayed.
     * @param postersListener the listener to handle poster selection actions.
     */
    public PosterAdapter(List<Poster> posterList, PostersListener postersListener) {
        this.posterList = posterList;
        this.postersListner= postersListener;
    }

    private PostersListener postersListner;
    class PosterViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout layoutPosters;

        View viewBackground;

        RoundedImageView imagePoster;

        TextView textName, textCreatedBy, textStory;

        RatingBar ratingBar;

        ImageView imageSelected;

        /**
         * Initializes the views used in the ViewHolder.
         * @param itemView The view of the individual item.
         */
        public PosterViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutPosters= itemView.findViewById(R.id.layoutPosters);
            viewBackground= itemView.findViewById(R.id.viewBackground);
            imagePoster= itemView.findViewById(R.id.imagePosters);
            textName = itemView.findViewById(R.id.textName);
            textCreatedBy = itemView.findViewById(R.id.textCreatedBy);
            textStory = itemView.findViewById(R.id.textStory);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            imageSelected = itemView.findViewById(R.id.imageSelected);
        }

        /**
         * Binds the data from a Poster object to the view components in the ViewHolder.
         * @param poster The Poster object containing data to bind to the ViewHolder.
         */
        void bindPoster(final Poster poster){
            imagePoster.setImageResource(poster.image);
            textName.setText(poster.name);
            textCreatedBy.setText(poster.createdBy);
            textStory.setText(poster.story);
            ratingBar.setRating(poster.rating);
            if (poster.isSelected){
                viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                imageSelected.setVisibility(View.VISIBLE);
            }else {
                viewBackground.setBackgroundResource(R.drawable.poster_background);
                imageSelected.setVisibility(View.GONE);
            }

            layoutPosters.setOnClickListener(new View.OnClickListener() {
                /**
                 * Handles the click event on the poster layout. Toggles the selection state of the poster
                 * and updates the background and visibility of the selection indicator.
                 * @param v The view that was clicked.
                 */
                @Override
                public void onClick(View v) {
                    if(poster.isSelected){
                        viewBackground.setBackgroundResource(R.drawable.poster_background);
                        imageSelected.setVisibility(View.GONE);
                        poster.isSelected=false;
                        if(getSelectedPosters().isEmpty()){
                            postersListner.onPosterAction(false);
                        }
                    }else{
                        viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                        imageSelected.setVisibility(View.VISIBLE);
                        poster.isSelected = true;
                        postersListner.onPosterAction(true);
                    }
                }
            });
        }



    }
}
