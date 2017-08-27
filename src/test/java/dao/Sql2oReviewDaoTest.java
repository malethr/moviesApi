package dao;

import models.Movie;
import models.Review;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by mariathomas on 8/26/17.
 */
public class Sql2oReviewDaoTest {

    private Connection conn;
    private Sql2oReviewDao reviewDao;
    private Sql2oMovieDao movieDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        reviewDao = new Sql2oReviewDao(sql2o);
        movieDao = new Sql2oMovieDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingReviewSetsId() throws Exception {
        Movie testMovie = setupMovie();
        movieDao.add(testMovie);
        Review testReview = new Review("Jane Doe","3","Good Movie",testMovie.getId());
        int originalReviewId = testReview.getId();
        reviewDao.add(testReview);
        assertNotEquals(originalReviewId,testReview.getId());
    }

    @Test
    public void getAllReviewsByMovie() throws Exception {
        Movie testMovie = setupMovie();
        movieDao.add(testMovie);
        Movie newMovie = setupAltMovie();
        movieDao.add(newMovie);
        Review testReview = new Review("Jane Doe","3","Good Movie", testMovie.getId());
        reviewDao.add(testReview);
        Review otherReview = new Review("John Smith","1","Bad Movie", testMovie.getId());
        reviewDao.add(otherReview);
        assertEquals(2, reviewDao.getAllReviewsByMovie(testMovie.getId()).size());
        assertEquals(0, reviewDao.getAllReviewsByMovie(newMovie.getId()).size());
    }

    @Test
    public void deleteReviewById() throws Exception {
        Movie testMovie = setupMovie();
        movieDao.add(testMovie);
        Movie newMovie = setupAltMovie();
        movieDao.add(newMovie);
        Review testReview = new Review("Jane Doe","3","Good Movie", testMovie.getId());
        reviewDao.add(testReview);
        Review otherReview = new Review("John Smith","1","Bad Movie", testMovie.getId());
        reviewDao.add(otherReview);
        reviewDao.deleteReviewById(testReview.getId());
        assertEquals(1, reviewDao.getAllReviewsByMovie(testMovie.getId()).size());
    }

    public Movie setupMovie (){
        return new Movie("Inside Job","Academy award winning documentary and for me the best movie on the Financial crisis made.","2010","Charles Ferguson","https://www.youtube.com/watch?v=Dzs3Xwnf9Pw");
    }

    public Movie setupAltMovie (){
        return new Movie("Inside Job","Academy award winning documentary and for me the best movie on the Financial crisis made.");
    }
}