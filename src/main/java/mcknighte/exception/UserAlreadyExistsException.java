package mcknighte.exception;

/**
 * UserAlreadyExistsException, exception thrown for when an attempt is made 
 * to create a user to the system which already exists
 *
 * @author Edward McKnight (UP608985)
 * @see UserException
 * @since 2017
 * @version 1.0
 */
public class UserAlreadyExistsException extends UserException {
    private final String userName;

    /**
     * Constructor
     *
     * @param userName the username of the user which was attempted to be created
     */
    public UserAlreadyExistsException(String userName) {
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
        return "A user with username: \"" + this.userName + "\" already exists.";
    }
}
