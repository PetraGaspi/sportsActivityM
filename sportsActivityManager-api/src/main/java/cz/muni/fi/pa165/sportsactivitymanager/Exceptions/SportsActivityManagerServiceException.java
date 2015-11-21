package cz.muni.fi.pa165.sportsactivitymanager.Exceptions;

/**
 *
 * @author Petra Gasparikova
 */
public class SportsActivityManagerServiceException extends RuntimeException{
    
    public SportsActivityManagerServiceException() {
    }

    public SportsActivityManagerServiceException(String message) {
        super(message);
    }

    public SportsActivityManagerServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public SportsActivityManagerServiceException(Throwable cause) {
        super(cause);
    }

    public SportsActivityManagerServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
