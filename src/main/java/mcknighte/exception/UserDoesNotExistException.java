package mcknighte.exception;

/**
 * UserDoesNotExistException
 * 
 * @author Edward McKnight (UP608985)
 */
public class UserDoesNotExistException extends UserException {
    private final String userName;
    
    /**
     * Constructor
     * 
     * @param userName String
     */
    public UserDoesNotExistException(String userName) {
        super();
        this.userName = userName;
    }
    
    /**
     * Get message
     * 
     * @return String
     */
    @Override
    public String getMessage() {
        return "A user with username: \"" + this.userName + "\" does not exist.";
    }
}
