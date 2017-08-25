package dao;

import models.Movie;

import java.util.List;

/**
 * Created by mariathomas on 8/25/17.
 */
public interface MovieDao {

    void add(Movie movie);  // Create
    List<Movie> getAll();   // Read
    Movie findById(int id); // Read by Id
    void update(int id, String title, String description, String year, String director, String trailer); // Update
    void deleteById(int id);// Delete

//    void addMovieToMovieType(Movie movie, MovieTypeDao movieType);
//    List<MovieTypeDao> getAllMovieTypesForAMovie(int movieId);
}
