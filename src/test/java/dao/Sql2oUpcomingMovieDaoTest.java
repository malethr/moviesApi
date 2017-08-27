package dao;

import models.MovieType;
import models.UpcomingMovie;
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
public class Sql2oUpcomingMovieDaoTest {
    private Sql2oUpcomingMovieDao upcomingMovieDao;
    private Sql2oMovieDao MovieDao;
    private Sql2oMovieTypeDao movieTypeDao;
    private Connection con;

    @Before
    public void setUp() throws Exception {
        String connectionString ="jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString,"","");
        upcomingMovieDao = new Sql2oUpcomingMovieDao(sql2o);
        MovieDao = new Sql2oMovieDao(sql2o);
        movieTypeDao = new Sql2oMovieTypeDao(sql2o);
        con = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        con.close();
    }

    @Test
    public void addingMovieSetsId() throws Exception {
        UpcomingMovie testUpcomingMovie = setupMovie();
        int originalMovieId = testUpcomingMovie.getId();
        upcomingMovieDao.add(testUpcomingMovie);
        assertNotEquals(originalMovieId,testUpcomingMovie.getId());
    }

    @Test
    public void addedMoviesAreReturnedFromGetAll() throws Exception {
        UpcomingMovie testUpcomingMovie = setupMovie();
        upcomingMovieDao.add(testUpcomingMovie);
        assertEquals(1, upcomingMovieDao.getAll(testUpcomingMovie.getMyear()).size());
    }

    @Test
    public void noUpcomingMoviesReturnsEmptyList() throws Exception {
        UpcomingMovie testUpcomingMovie = setupMovie();
        assertEquals(0, upcomingMovieDao.getAll(testUpcomingMovie.getMyear()).size());
    }

    @Test
    public void existingUpcomingMoviesCanBeFoundByYear() throws Exception {
        UpcomingMovie testUpcomingMovie = setupMovie();
        upcomingMovieDao.add(testUpcomingMovie);
        UpcomingMovie foundUpcomingMovie = upcomingMovieDao.findUpcomingMovieByYear(testUpcomingMovie.getMyear());
        assertEquals(testUpcomingMovie, foundUpcomingMovie);
    }

    @Test
    public void updateChangesUpcomingMovieContent() throws Exception {
        UpcomingMovie testUpcomingMovie = setupMovie();
        upcomingMovieDao.add(testUpcomingMovie);
        upcomingMovieDao.updateUpcomingMovie(testUpcomingMovie.getId(),"Aquaman","Arthur Curry learns that he is the heir to the underwater kingdom of Atlantis, and must step forward to lead his people and to be a hero to the world.","2018","James Wan","","May 2, 2017");
        UpcomingMovie updatedUpcomingMovie = upcomingMovieDao.findUpcomingMovieById(testUpcomingMovie.getId());
        assertNotEquals(testUpcomingMovie, updatedUpcomingMovie);
    }

    @Test
    public void deleteByIdDeletesCorrectMovie() throws Exception {
        UpcomingMovie testUpcomingMovie = setupMovie();
        UpcomingMovie otherUpcomingMovie = setupAltMovie();
        upcomingMovieDao.add(testUpcomingMovie);
        upcomingMovieDao.add(otherUpcomingMovie);
        assertEquals(2, upcomingMovieDao.getAll("2018").size());
        upcomingMovieDao.deleteUpcomingMovieById(testUpcomingMovie.getId());
        assertEquals(1, upcomingMovieDao.getAll("2018").size());
    }

    public UpcomingMovie setupMovie (){
        return new UpcomingMovie("Tomb Raider","Lara Croft, the fiercely independent daughter of a missing adventurer.","2018","Roar Uthaug","https://www.youtube.com/watch?v=rK6t9W0CL7w","March 16, 2018");
    }

    public UpcomingMovie setupAltMovie (){
        return new UpcomingMovie("Magic Camp","Follows Andy, who, at the urging of his former mentor and Magic Camp owner Roy Preston, returns as a counselor to the camp of his youth hoping to reignite his career.","2018","Mark Waters","https://www.youtube.com/watch?v=uurWMBRiOJ4","April 6, 2018");
    }

    public MovieType setupMovieType (){
        return new MovieType("Finance");
    }

    public MovieType setupAltMovieType (){
        return new MovieType("Biography");
    }
}