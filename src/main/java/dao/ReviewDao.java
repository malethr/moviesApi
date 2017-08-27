package dao;

import models.Review;

import java.util.List;

/**
 * Created by mariathomas on 8/25/17.
 */
public interface ReviewDao {

    void add(Review review);  // Create
    List<Review> getAllReviewsByMovie(int movieId);   // Read
    //    void update(int id, String name); // Update
    void deleteReviewById(int id);// Delete
}
