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
public class Sql2oMovieDao implements MovieDao{

    private final Sql2o sql2o;

    public Sql2oMovieDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Movie movie) {
        String sql = "INSERT INTO movies (title, description, year, director, trailer) VALUES (:title, :description, :year, :director, :trailer)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .bind(movie)
                    .executeUpdate()
                    .getKey();
            movie.setId(id);
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Movie> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM movies")
                    .executeAndFetch(Movie.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM movies WHERE id = :id";
        String deleteJoin = "DELETE FROM movies_movietypes WHERE movieid = :movieid";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("movieid", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public Movie findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM movies WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Movie.class);
        }
    }

    @Override
    public void update(int id, String newTitle, String newDescription, String newYear, String newDirector, String newTrailer){
        String sql = "UPDATE movies SET (title, description, year, director, trailer) = (:title, :description, :year, :director, :trailer) WHERE id=:id";

        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("title", newTitle)
                    .addParameter("description", newDescription)
                    .addParameter("year", newYear)
                    .addParameter("director", newDirector)
                    .addParameter("trailer", newTrailer)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void addMovieToMovieType(Movie movie, MovieType movieType){
        String sql = "INSERT INTO movies_movietypes (movieid, movietypeid) VALUES (:movieid, :movietypeid)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("movieid", movie.getId())
                    .addParameter("movietypeid", movieType.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List <MovieType> getAllMovieTypesForAMovie(int movieId) {
        ArrayList<MovieType> movieTypes = new ArrayList<>();

        String joinQuery = "SELECT movietypeid FROM movies_movietypes WHERE movieid = :movieid";

        try (Connection con = sql2o.open()) {
            List<Integer> allMovieTypesIds = con.createQuery(joinQuery)
                    .addParameter("movieid", movieId)
                    .executeAndFetch(Integer.class);
            for (Integer typeId : allMovieTypesIds){
                String movietypeQuery = "SELECT * FROM movietypes WHERE id = :movietypeid";
                movieTypes.add(
                        con.createQuery(movietypeQuery)
                                .addParameter("movietypeid", typeId)
                                .executeAndFetchFirst(MovieType.class));
            }
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return movieTypes;
    }

}
