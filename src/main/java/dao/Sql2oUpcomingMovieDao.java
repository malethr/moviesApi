package dao;

import models.UpcomingMovie;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

/**
 * Created by mariathomas on 8/26/17.
 */
public class Sql2oUpcomingMovieDao implements UpcomingMovieDao{
    private final Sql2o sql2o;

    public Sql2oUpcomingMovieDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(UpcomingMovie upcomingMovie) {
        String sql = "INSERT INTO movies (title, description, myear, director, trailer, intheaters) VALUES (:title, :description, :myear, :director, :trailer, :inTheaters)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .bind(upcomingMovie)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
            upcomingMovie.setId(id);
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<UpcomingMovie> getAll(String myear) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM movies WHERE myear = :myear")
                    .addParameter("myear", myear)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(UpcomingMovie.class);
        }
    }

    @Override
    public void deleteUpcomingMovieById(int id) {
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
    public UpcomingMovie findUpcomingMovieByYear(String myear) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM movies WHERE myear = :myear")
                    .addParameter("myear", myear)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(UpcomingMovie.class);
        }
    }

    @Override
    public UpcomingMovie findUpcomingMovieById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM movies WHERE id = :id")
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(UpcomingMovie.class);
        }
    }

    @Override
    public void updateUpcomingMovie(int id, String newTitle, String newDescription, String newYear, String newDirector, String newTrailer, String newIntheaters){
        String sql = "UPDATE movies SET (title, description, myear, director, trailer, intheaters) = (:title, :description, :myear, :director, :trailer, :intheaters) WHERE id=:id";

        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("title", newTitle)
                    .addParameter("description", newDescription)
                    .addParameter("myear", newYear)
                    .addParameter("director", newDirector)
                    .addParameter("trailer", newTrailer)
                    .addParameter("intheaters", newIntheaters)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
