package dao;

import models.MovieType;

import java.util.List;

/**
 * Created by mariathomas on 8/25/17.
 */
public interface MovieTypeDao {

    void add(MovieType movieType);  // Create
    List<MovieType> getAll();   // Read
    MovieType findById(int id); // Read by Id
//    void update(int id, String name); // Update
    void deleteById(int id);// Delete

//    void addMovieTypeToMovie(MovieType movieType, Movie movie); // add movie type to movie
//    List<Movie> getAllMovieForAMovieTypes(int id); // get all movie list from movie type
}
