package models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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

    public Review(String writtenBy, int rating, Timestamp createdAt, int id, int movieId, String content) {
        this.writtenBy = writtenBy;
        this.rating = rating;
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
        this.id = id;
        this.movieId = movieId;
        this.content = content;
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

    public Timestamp getCreatedAt() {
        return createdAt;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (rating != review.rating) return false;
        if (id != review.id) return false;
        if (movieId != review.movieId) return false;
        if (!writtenBy.equals(review.writtenBy)) return false;
        return content.equals(review.content);
    }

    @Override
    public int hashCode() {
        int result = writtenBy.hashCode();
        result = 31 * result + rating;
        result = 31 * result + id;
        result = 31 * result + movieId;
        result = 31 * result + content.hashCode();
        return result;
    }
}
