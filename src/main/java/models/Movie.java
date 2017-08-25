package models;

/**
 * Created by mariathomas on 8/25/17.
 */
public class Movie {
    private String title;
    private String description;
    private String releaseYear;
    private String directedBy;
    private String trailer;
    private int id;

    public Movie(String title, String description) {
        this.title = title;
        this.description = description;
        this.releaseYear = "not available";
        this.directedBy = "not available";
        this.trailer = "not available";
    }

    public Movie(String title, String description, String releaseYear, String directedBy, String trailer) {
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.directedBy = directedBy;
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

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDirectedBy() {
        return directedBy;
    }

    public void setDirectedBy(String directedBy) {
        this.directedBy = directedBy;
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
        if (releaseYear != null ? !releaseYear.equals(movie.releaseYear) : movie.releaseYear != null) return false;
        if (directedBy != null ? !directedBy.equals(movie.directedBy) : movie.directedBy != null) return false;
        return trailer != null ? trailer.equals(movie.trailer) : movie.trailer == null;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + (releaseYear != null ? releaseYear.hashCode() : 0);
        result = 31 * result + (directedBy != null ? directedBy.hashCode() : 0);
        result = 31 * result + (trailer != null ? trailer.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
