package mcknighte.exception;

/**
 * UserAlreadyExistsException
 * 
 * @author Edward McKnight (UP608985)
 */
public class UserAlreadyExistsException extends UserException {
    private final String userName;
    
    /**
     * Constructor
     * 
     * @param userName String
     */
    public UserAlreadyExistsException(String userName) {
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
        return "A user with username: \"" + this.userName + "\" already exists.";
    }
}