package dao;

import models.Movie;
import models.MovieType;

import java.util.List;

/**
 * Created by mariathomas on 8/25/17.
 */
public interface MovieTypeDao {

    void add(MovieType movieType);  // Create
    void addMovieTypeToMovie(MovieType movieType, Movie movie); // add movie type to movie

    List<MovieType> getAll();   // Read
    List<Movie> getAllMovieByMovieTypes(int id); // get all movie list from movie type

    MovieType findById(int id); // Read by Id
//    void update(int id, String type); // Update
    void deleteById(int id);    // Delete
}
