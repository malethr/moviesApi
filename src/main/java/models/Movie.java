package models;

/**
 * Created by mariathomas on 8/25/17.
 */
public class Movie {
    private String title;
    private String description;
    private String year;
    private String director;
    private String trailer;
    private int id;

    public Movie(String title, String description) {
        this.title = title;
        this.description = description;
        this.year = "not available";
        this.director = "not available";
        this.trailer = "not available";
    }

    public Movie(String title, String description, String year, String director, String trailer) {
        this.title = title;
        this.description = description;
        this.year = year;
        this.director = director;
        this.trailer = trailer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (id != movie.id) return false;
        if (!title.equals(movie.title)) return false;
        if (!description.equals(movie.description)) return false;
        if (year != null ? !year.equals(movie.year) : movie.year != null) return false;
        if (director != null ? !director.equals(movie.director) : movie.director != null) return false;
        return trailer != null ? trailer.equals(movie.trailer) : movie.trailer == null;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + (trailer != null ? trailer.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
