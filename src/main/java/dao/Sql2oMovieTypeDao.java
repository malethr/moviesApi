package dao;

import models.Movie;
import models.MovieType;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mariathomas on 8/25/17.
 */
public class Sql2oMovieTypeDao implements MovieTypeDao{

    private final Sql2o sql2o;

    public Sql2oMovieTypeDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(MovieType movietype){
        String sql = "INSERT INTO movietypes (type) VALUES (:type)";
        try (Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql)
                    .bind(movietype)
                    .executeUpdate()
                    .getKey();
            movietype.setId(id);
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<MovieType> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM movietypes")
                    .executeAndFetch(MovieType.class);
        }
    }

    @Override
    public MovieType findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM movietypes WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(MovieType.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM movietypes WHERE id = :id";
        String deleteJoin = "DELETE FROM movies_movietypes WHERE movietypeid = :movietypeid";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("movietypeid", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void addMovieTypeToMovie(MovieType movieType, Movie movie) {
        String sql = "INSERT INTO movies_movietypes (movieid, movietypeid) VALUES (:movieid, :movietypeid)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("movieid", movie.getId())
                    .addParameter("movietypeid", movieType.getId())
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Movie> getAllMovieByMovieTypes(int movietypeid) {
        ArrayList<Movie> restaurants = new ArrayList<>();
        String joinQuery = "SELECT movieid FROM movies_movietypes WHERE movietypeid = :movietypeid";
        try (Connection con = sql2o.open()) {
            List<Integer> allMovieIds = con.createQuery(joinQuery)
                    .addParameter("movietypeid", movietypeid)
                    .executeAndFetch(Integer.class); //what is happening in the lines above?
            for (Integer movieid : allMovieIds) {
                String restaurantQuery = "SELECT * FROM movies WHERE id = :movieid";
                restaurants.add(
                        con.createQuery(restaurantQuery)
                                .addParameter("movieid", movieid)
                                .throwOnMappingFailure(false)
                                .executeAndFetchFirst(Movie.class));
            }
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
        return restaurants;
    }
}
