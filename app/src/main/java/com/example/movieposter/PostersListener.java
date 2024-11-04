package com.example.movieposter;

/**
 * Interface for handling actions related to movie posters.
 * Used to define a callback for poster selection events.
 */
public interface PostersListener {
    /**
     * Callback method invoked when a poster is selected or deselected.
     *
     * @param isSelected True if a poster is selected, false otherwise.
     */
    void onPosterAction(Boolean isSelected);
}
