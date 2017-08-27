package models;

/**
 * Created by mariathomas on 8/26/17.
 */
public class UpcomingMovie extends Movie {

    private String inTheaters;


    public UpcomingMovie(String title, String description, String myear, String director, String trailer, String inTheaters) {
        super(title, description, myear, director, trailer);
        this.inTheaters=inTheaters;
    }

    public String getInTheaters() {
        return inTheaters;
    }

    public void setInTheaters(String inTheaters) {
        this.inTheaters = inTheaters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UpcomingMovie that = (UpcomingMovie) o;

        return inTheaters.equals(that.inTheaters);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + inTheaters.hashCode();
        return result;
    }
}
