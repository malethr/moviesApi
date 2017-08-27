package dao;

import models.Movie;
import models.MovieType;

import java.util.List;

/**
 * Created by mariathomas on 8/25/17.
 */
public interface MovieDao {

    void add(Movie movie);  // Create
    void addMovieToMovieType(Movie movie, MovieType movieType);

    List<Movie> getAll();   // Read
    List<MovieType> getAllMovieTypesForAMovie(int movieId);

    Movie findById(int id); // Read by Id
    void update(int id, String title, String description, String myear, String director, String trailer); // Update
    void deleteById(int id);// Delete
}
