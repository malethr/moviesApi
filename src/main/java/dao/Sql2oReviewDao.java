package dao;

import models.Review;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

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
        String sql = "INSERT INTO reviews (writtenby, rating, movieid) VALUES (:writtenby, :rating, :movieid)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .addParameter("writtenby", review.getWrittenBy())
                    .addParameter("rating", review.getRating())
                    .addParameter("movieid", review.getMovieId())
//                    .addParameter("createdat", "createdat")
//                    .addColumnMapping("CREATEDAT", "createdat")
                    .addColumnMapping("WRITTENBY", "writtenby")
                    .addColumnMapping("RATING", "rating")
                    .addColumnMapping("RESTAURANTID", "movieid")
                    .executeUpdate()
                    .getKey();
            review.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
