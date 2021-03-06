package models;

/**
 * Created by mariathomas on 8/25/17.
 */
public class MovieType {
    private String type;
    private int id;

    public MovieType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

        MovieType movieType = (MovieType) o;

        if (id != movieType.id) return false;
        return type.equals(movieType.type);
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + id;
        return result;
    }
}
