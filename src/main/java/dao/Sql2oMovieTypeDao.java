package dao;

import models.MovieType;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

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
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
