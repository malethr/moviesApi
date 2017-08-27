import com.google.gson.Gson;
import dao.Sql2oMovieDao;
import dao.Sql2oMovieTypeDao;
import dao.Sql2oReviewDao;
import dao.Sql2oUpcomingMovieDao;
import models.Movie;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

/**
 * Created by mariathomas on 8/26/17.
 */
public class App {
    public static void main(String[] args) {
        Sql2oMovieDao movieDao;
        Sql2oMovieTypeDao movieTypeDao;
        Sql2oUpcomingMovieDao upcomingMovieDao;
        Sql2oReviewDao reviewDao;
        org.sql2o.Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/movie.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        movieDao = new Sql2oMovieDao(sql2o);
        movieTypeDao = new Sql2oMovieTypeDao(sql2o);
        reviewDao = new Sql2oReviewDao(sql2o);
        upcomingMovieDao = new Sql2oUpcomingMovieDao(sql2o);
        conn = sql2o.open();

        //post: create new movie
        post("/movies/new", "application/json", (req, res) -> {
            Movie movie = gson.fromJson(req.body(), Movie.class);
            movieDao.add(movie);
            res.status(201);
            res.type("application/joson");
            return gson.toJson(movie);
        });

        //get: read posted movies
        get("/movies", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            return gson.toJson(movieDao.getAll());//send it back to be displayed
        });

        //get: read posted movies per id
        get("/movies/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            int movieId = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(movieDao.findById(movieId));
        });



        //Filters
        exception(ApiException.class, (exception, req, res) -> {
            ApiException err = (ApiException) exception;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json");
            res.status(err.getStatusCode());
            res.body(gson.toJson(jsonMap));
        });

        after((req, res) ->{
            res.type("application/json");
        });
    }
}
