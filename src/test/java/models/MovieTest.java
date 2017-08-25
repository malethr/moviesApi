package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by mariathomas on 8/25/17.
 */
public class MovieTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getMovieTitleReturnsCorrectMovieTitle() throws Exception {
        Movie testMovie = setupMovie();
        assertEquals("Inside Job", testMovie.getTitle());
    }

    @Test
    public void getDescriptionReturnsCorrectDescription() throws Exception {
        Movie testMovie = setupMovie();
        assertEquals("Academy award winning documentary and for me the best movie on the Financial crisis made.", testMovie.getDescription());
    }

    @Test
    public void getYearReturnsCorrectYear() throws Exception {
        Movie testMovie = setupMovie();
        assertEquals("2010", testMovie.getYear());
    }
    @Test
    public void getDirectorReturnsCorrectDirector() throws Exception {
        Movie testMovie = setupMovie();
        assertEquals("Charles Ferguson", testMovie.getDirector());
    }

    @Test
    public void getTrailerReturnsCorrectTrailer() throws Exception {
        Movie testMovie = setupAltMovie();
        assertEquals("not available", testMovie.getTrailer());
    }

    @Test
    public void setTitleSetsCorrectTitle() throws Exception {
        Movie testMovie = setupMovie();
        testMovie.setTitle("The Wolf of Wall Street");
        assertNotEquals("Inside Job",testMovie.getTitle());
    }

    @Test
    public void setDescriptionSetsCorrectDescription() throws Exception {
        Movie testMovie = setupMovie();
        testMovie.setDescription("Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption and the federal government.");
        assertNotEquals("Academy award winning documentary and for me the best movie on the Financial crisis made.", testMovie.getDescription());
    }

    @Test
    public void setYearSetsCorrectYear() throws Exception {
        Movie testMovie = setupMovie();
        testMovie.setYear("2013");
        assertNotEquals("2010", testMovie.getYear());
    }
    @Test
    public void setDirectedBySetsCorrectDirector() throws Exception {
        Movie testMovie = setupMovie();
        testMovie.setDirector("Martin Scorsese");
        assertNotEquals("Charles Ferguson", testMovie.getDirector());
    }

    @Test
    public void setTrailerSetsCorrectTrailer() throws Exception {
        Movie testMovie = setupMovie();
        testMovie.setTrailer("https://www.youtube.com/watch?v=iszwuX1AK6A");
        assertNotEquals("https://www.youtube.com/watch?v=Dzs3Xwnf9Pw", testMovie.getTrailer());
    }


    public Movie setupMovie (){
        return new Movie("Inside Job","Academy award winning documentary and for me the best movie on the Financial crisis made.","2010","Charles Ferguson","https://www.youtube.com/watch?v=Dzs3Xwnf9Pw");
    }

    public Movie setupAltMovie (){
        return new Movie("Inside Job","Academy award winning documentary and for me the best movie on the Financial crisis made.");
    }

}