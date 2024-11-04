package com.example.movieposter;

/**
 * Model class representing a movie poster.
 * Contains details about the poster, including name, creator, story, image, selection status, and rating.
 */
public class Poster {

    /** The name of the movie, creator, and story */
    String name, createdBy, story;

    /** The image resource ID for the movie poster. */
    int image;

    /** Indicates whether the poster is selected. Default is false. */
    Boolean isSelected = false;

    /** The rating of the movie on a scale, e.g., out of 5 stars. */
    float rating;
}
