package dao;

import models.Movie;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

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
    public void deleteById(int id) {
        String sql = "DELETE FROM movies WHERE id = :id"; //raw sql
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

}
