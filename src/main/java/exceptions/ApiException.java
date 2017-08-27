package exceptions;

/**
 * Created by mariathomas on 8/27/17.
 */
public class ApiException extends RuntimeException{

    private final int statusCode;

    public ApiException(int statusCode, String msg){
        super(msg);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
