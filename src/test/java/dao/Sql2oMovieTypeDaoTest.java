package dao;

import models.MovieType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by mariathomas on 8/25/17.
 */
public class Sql2oMovieTypeDaoTest {

    private Sql2oMovieTypeDao movieTypeDao;
    private Sql2oMovieDao movieDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        movieDao = new Sql2oMovieDao(sql2o);
        movieTypeDao = new Sql2oMovieTypeDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingMovieTypeSetsId() throws Exception {
        MovieType testMovieType = setupMovieType();
        int originalMovieTypeId = testMovieType.getId();
        movieTypeDao.add(testMovieType);
        assertNotEquals(originalMovieTypeId,testMovieType.getId());
    }

    @Test
    public void addedMovieTypesAreReturnedFromGetAll() throws Exception {
        MovieType testMovieType = setupMovieType();
        movieTypeDao.add(testMovieType);
        assertEquals(1, movieTypeDao.getAll().size());
    }

    @Test
    public void noMovieTypesReturnsEmptyList() throws Exception {
        assertEquals(0, movieTypeDao.getAll().size());
    }

    @Test
    public void existingMovieTypesCanBeFoundById() throws Exception {
        MovieType movieType = setupMovieType();
        movieTypeDao.add(movieType);
        MovieType foundMovieType = movieTypeDao.findById(movieType.getId());
        assertEquals(movieType, foundMovieType);
    }

    @Test
    public void deleteByIdDeletesCorrectMovieType() throws Exception {
        MovieType testMovieType = setupMovieType();
        MovieType altMovieType = setupAltMovieType();
        movieTypeDao.add(testMovieType);
        movieTypeDao.add(altMovieType);
        movieTypeDao.deleteById(testMovieType.getId());
        assertEquals(1, movieTypeDao.getAll().size());
        assertEquals(altMovieType,movieTypeDao.findById(altMovieType.getId()));
        assertNotEquals(testMovieType,movieTypeDao.findById(testMovieType.getId()));
    }


    public MovieType setupMovieType (){
        return new MovieType("Finance");
    }

    public MovieType setupAltMovieType (){
        return new MovieType("Biography");
    }

}