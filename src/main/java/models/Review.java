package models;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by mariathomas on 8/25/17.
 */
public class Review {

    private String writtenBy;
    private int rating;
    private Timestamp createdAt;
    private int id;
    private int movieId;
    private String content;


    public Review(String writtenBy, int rating, String content, int movieId) {
        this.writtenBy = writtenBy;
        this.rating = rating;
        this.createdAt =  Timestamp.valueOf(LocalDateTime.now());
        this.content = content;
        this.movieId = movieId;
    }

    public String getWrittenBy() {
        return writtenBy;
    }

    public void setWrittenBy(String writtenBy) {
        this.writtenBy = writtenBy;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getCreatedAt() {
        Date createdAtAsDate = this.createdAt;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(createdAtAsDate);
        return formattedDate;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
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
