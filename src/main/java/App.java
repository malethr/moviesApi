import com.google.gson.Gson;
import dao.Sql2oMovieDao;
import dao.Sql2oMovieTypeDao;
import dao.Sql2oReviewDao;
import dao.Sql2oUpcomingMovieDao;
import exceptions.ApiException;
import models.Movie;
import models.MovieType;
import models.Review;
import models.UpcomingMovie;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.List;
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
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/movies.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
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
            List <Movie> movie = movieDao.getAll();
            if ( movie.isEmpty()){
                throw new ApiException(404, String.format("No movie listed yet"));
            }
            return gson.toJson(movieDao.getAll());//send it back to be displayed
        });

        //get: read posted movies per id
        get("/movies/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            int movieId = Integer.parseInt(req.params("id"));
            Movie movieToFind = movieDao.findById(movieId);
            res.type("application/json");
            if ( movieToFind == null){
                throw new ApiException(404, String.format("No movie with the id: %s exists", req.params("id")));
            }
            return gson.toJson(movieDao.findById(movieId));
        });

        //post: create new upcoming movie
        post("/upcomingmovies/new", "application/json", (req, res) -> {
            UpcomingMovie upcomingMovie = gson.fromJson(req.body(), UpcomingMovie.class);
            upcomingMovieDao.add(upcomingMovie);
            res.status(201);
            res.type("application/joson");
            String myear = upcomingMovie.getMyear();
            int year = Integer.parseInt(myear);
            if (year <= 2017){
                throw new ApiException(404, String.format("Year should be greater than 2017"));
            }
            return gson.toJson(upcomingMovie);
        });

        //get: read posted upcoming movies by year
        get("/upcomingmovies/:myear", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            String upcomingMovieYear = (req.params("myear"));
            UpcomingMovie upcomingMovie = upcomingMovieDao.findUpcomingMovieByYear(upcomingMovieYear);
            res.type("application/json");
            if (upcomingMovie == null){
                throw new ApiException(404, String.format("No upcomingMovie available in year: %s exists", req.params("myear")));
            }
            return gson.toJson(upcomingMovieDao.getAll(upcomingMovieYear));//send it back to be displayed
        });

        //post: create reviews
        post("/movies/:movieid/reviews/new", "application/json", (req, res) -> {
            int movieid = Integer.parseInt(req.params("movieid"));
            Review review = gson.fromJson(req.body(), Review.class);
            review.setMovieId(movieid);
            reviewDao.add(review);
            res.status(201);
            res.type("application/json");
            return gson.toJson(review);
        });

        //get: read reviews by movie id
        get("/movies/:movieId/reviews", "application/json",(req, res) -> {
            int movieId = Integer.parseInt(req.params("movieId"));
            return gson.toJson(reviewDao.getAllReviewsByMovie(movieId));
        });

        //post: create movietype
        post("/movietypes/new", "application/joson",(req,res)->{
            MovieType movieType = gson.fromJson(req.body(), MovieType.class);
            movieTypeDao.add(movieType);
            res.status(201);
            return gson.toJson(movieType);
        });

        //get: read all movietypes
        get("/movietypes", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            return gson.toJson(movieTypeDao.getAll());//send it back to be displayed
        });

        //get: get to assign movietype to movie
        get("/movies/:movieId/movietype/:movieTypeId", "application/json", (req,res)->{
           int movieId = Integer.parseInt(req.params("movieId"));
           int movieTypeId = Integer.parseInt(req.params("movieTypeId"));
           Movie movie = movieDao.findById(movieId);
           MovieType movieType = movieTypeDao.findById(movieTypeId);
           movieTypeDao.addMovieTypeToMovie(movieType, movie);
           return gson.toJson(movieDao.getAllMovieTypesForAMovie(movieId));
        });

        //get: all movietypes per movie
        get("/movies/:movieId/movietypes", "application/json", (req,res)->{
            int movieId = Integer.parseInt(req.params("movieId"));
            return gson.toJson(movieDao.getAllMovieTypesForAMovie(movieId));
        });

        //get: delete movie by Id
        get("/movies/:movieId/delete","application/json",(req,res)->{
           int movieId = Integer.parseInt(req.params("movieId"));
            movieDao.deleteById(movieId);
           return gson.toJson(movieDao.getAll());
        });

        //get: delete movietype by Id
        get("/movietypes/:movieTypeId/delete","application/json",(req,res)->{
            int movieTypeId = Integer.parseInt(req.params("movieTypeId"));
            movieTypeDao.deleteById(movieTypeId);
            return gson.toJson(movieTypeDao.getAll());
        });

        //FILTERS
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
