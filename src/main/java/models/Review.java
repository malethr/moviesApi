package models;

/**
 * Created by mariathomas on 8/25/17.
 */
public class Review {

    private String writtenBy;
    private String rating;
    private int id;
    private int movieId;
    private String content;


    public Review(String writtenBy, String rating, String content, int movieId) {
        this.writtenBy = writtenBy;
        this.rating = rating;
        this.content = content;
        this.movieId = movieId;
    }

    public String getWrittenBy() {
        return writtenBy;
    }

    public void setWrittenBy(String writtenBy) {
        this.writtenBy = writtenBy;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
