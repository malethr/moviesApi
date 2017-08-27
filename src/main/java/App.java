import com.google.gson.Gson;
import dao.Sql2oMovieDao;
import dao.Sql2oMovieTypeDao;
import dao.Sql2oReviewDao;
import dao.Sql2oUpcomingMovieDao;

/**
 * Created by mariathomas on 8/26/17.
 */
public class App {
    public static void main(String[] args) {
        Sql2oMovieDao movieDao;
        Sql2oMovieTypeDao movieTypeDao;
        Sql2oUpcomingMovieDao upcomingMovieDao;
        Sql2oReviewDao reviewDao;
        org.sql2o.Connection conn;
        Gson gson = new Gson();

        String connectString = "jdbc:h2:~/jadle.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";

    }
}
