package dao;

import models.UpcomingMovie;

import java.util.List;

/**
 * Created by mariathomas on 8/26/17.
 */
public interface UpcomingMovieDao{
    void add(UpcomingMovie upcomingMovie);  // Create

    List<UpcomingMovie> getAll(String nextYear);   // Read

    UpcomingMovie findUpcomingMovieById(int id); // Read by Id
    void updateUpcomingMovie(int id, String title, String description, String myear, String director, String trailer, String inTheaters); // Update
    void deleteUpcomingMovieById(int id);// Delete
}
