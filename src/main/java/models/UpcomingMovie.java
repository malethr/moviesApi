package models;

import java.util.Date;

/**
 * Created by mariathomas on 8/26/17.
 */
public class Studio extends Movie {

    private String name;
    private int movieId;

    public Studio(String title, String description, String year, String director, String trailer) {
        this.title = title;
        this.description = description;
        this.year = year;
        this.director = director;
        this.trailer = trailer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }


}
