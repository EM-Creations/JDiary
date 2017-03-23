package mcknighte.exception;

/**
 * UserDoesNotExistException, exception thrown for when a user is found to 
 * not exist
 *
 * @author Edward McKnight (UP608985)
 * @see UserException
 * @since 2017
 * @version 1.0
 */
public class UserDoesNotExistException extends UserException {
    private final String userName;

    /**
     * Constructor
     *
     * @param userName the username which has been found to not exist
     */
    public UserDoesNotExistException(String userName) {
        super();
        this.userName = userName;
    }

    /**
     * Get the message generated for this exception
     *
     * @return the message
     */
    @Override
    public String getMessage() {
        return "A user with username: \"" + this.userName + "\" does not exist.";
    }
}
