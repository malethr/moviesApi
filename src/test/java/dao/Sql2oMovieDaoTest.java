package dao;

import models.Movie;
import models.MovieType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by mariathomas on 8/25/17.
 */
public class Sql2oMovieDaoTest {

    private Connection conn;
    private Sql2oMovieDao movieDao;
    private Sql2oMovieTypeDao movieTypeDao;
    private Sql2oReviewDao reviewDao;


    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString,"","");
        movieDao = new Sql2oMovieDao(sql2o);
        movieTypeDao = new Sql2oMovieTypeDao(sql2o);
        reviewDao = new Sql2oReviewDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingMovieSetsId() throws Exception {
        Movie testMovie = setupMovie();
        int originalMovieId = testMovie.getId();
        movieDao.add(testMovie);
        assertNotEquals(originalMovieId,testMovie.getId());
    }

    @Test
    public void addedMoviesAreReturnedFromGetAll() throws Exception {
        Movie testMovie = setupMovie();
        movieDao.add(testMovie);
        assertEquals(1, movieDao.getAll().size());
    }

    @Test
    public void noMoviesReturnsEmptyList() throws Exception {
        assertEquals(0, movieDao.getAll().size());
    }

    @Test
    public void existingMoviesCanBeFoundById() throws Exception {
        Movie movie = setupMovie();
        movieDao.add(movie);
        Movie foundMovie = movieDao.findById(movie.getId());
        assertEquals(movie, foundMovie);
    }

    @Test
    public void updateChangesMovieContent() throws Exception {
        Movie movie = setupMovie();
        movieDao.add(movie);
        movieDao.update(movie.getId(), "The Founder","The story of Ray Kroc","not available","not available","not available");
        Movie updatedMovie = movieDao.findById(movie.getId());
        assertNotEquals(movie, updatedMovie);
    }

    @Test
    public void deleteByIdDeletesCorrectMovie() throws Exception {
        Movie testMovie = setupMovie();
        Movie altMovie = setupAltMovie();
        movieDao.add(testMovie);
        movieDao.add(altMovie);
        movieDao.deleteById(testMovie.getId());
        assertEquals(1, movieDao.getAll().size());
    }

    @Test
    public void getAllMovietypesForAMoviesReturnsMovietypesCorrectly() throws Exception {
        MovieType testMovieType  = setupMovieType();
        movieTypeDao.add(testMovieType);

        MovieType otherMovieType  = setupAltMovieType();
        movieTypeDao.add(otherMovieType);

        Movie testMovie = setupMovie();
        movieDao.add(testMovie);
        movieDao.addMovieToMovieType(testMovie,testMovieType);
        movieDao.addMovieToMovieType(testMovie,otherMovieType);
        System.out.println(movieDao.getAllMovieTypesForAMovie(testMovie.getId()));

        MovieType[] movieTypes = {testMovieType, otherMovieType}; //oh hi what is this?

        assertEquals(movieDao.getAllMovieTypesForAMovie(testMovie.getId()), Arrays.asList(movieTypes));
    }

    @Test
    public void deleteingMovieAlsoUpdatesJoinTable() throws Exception {
        MovieType testMovieType  = setupMovieType();
        movieTypeDao.add(testMovieType);

        Movie testMovie = setupMovie();
        movieDao.add(testMovie);

        Movie altMovie = setupAltMovie();
        movieDao.add(altMovie);

        movieDao.addMovieToMovieType(testMovie,testMovieType);
        movieDao.addMovieToMovieType(altMovie, testMovieType);

        movieDao.deleteById(testMovie.getId());
        assertEquals(0, movieDao.getAllMovieTypesForAMovie(testMovie.getId()).size());
    }

    public Movie setupMovie (){
        return new Movie("Inside Job","Academy award winning documentary and for me the best movie on the Financial crisis made.","2010","Charles Ferguson","https://www.youtube.com/watch?v=Dzs3Xwnf9Pw");
    }

    public Movie setupAltMovie (){
        return new Movie("Inside Job","Academy award winning documentary and for me the best movie on the Financial crisis made.");
    }

    public MovieType setupMovieType (){
        return new MovieType("Finance");
    }

    public MovieType setupAltMovieType (){
        return new MovieType("Biography");
    }
}