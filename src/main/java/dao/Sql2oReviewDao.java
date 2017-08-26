package dao;

import models.Review;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

/**
 * Created by mariathomas on 8/25/17.
 */
public class Sql2oReviewDao implements ReviewDao{

    private final Sql2o sql2o;

    public Sql2oReviewDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Review review) {
        String sql = "INSERT INTO reviews (writtenby, rating, content, createdat, movieid) VALUES (:writtenby, :rating, :content, :createdat, :movieid)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .addParameter("writtenby", review.getWrittenBy())
                    .addParameter("rating", review.getRating())
                    .addParameter("movieid", review.getMovieId())
                    .addParameter("content", review.getContent())
                    .addParameter("createdat", review.getCreatedAt())
                    .addColumnMapping("WRITTENBY", "writtenby")
                    .addColumnMapping("RATING", "rating")
                    .addColumnMapping("MOVIEID", "movieid")
                    .addColumnMapping("CONTENT", "content")
                    .addColumnMapping("CREATEDAT", "createdat")
                    .executeUpdate()
                    .getKey();
            review.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from reviews WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Review> getAllReviewsByMovie(int movieId) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM reviews WHERE movieid = :movieid")
                    .addColumnMapping("MOVIEID","movieId")
                    .addParameter("movieid", movieId)
                    .executeAndFetch(Review.class);
        }
    }
}
