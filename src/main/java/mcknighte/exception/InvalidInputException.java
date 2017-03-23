package mcknighte.exception;

/**
 * InvalidInputException abstract exception class for input
 * related exceptions
 *
 * @author Edward McKnight (UP608985)
 * @since 2017
 * @version 1.0
 */
public abstract class InvalidInputException extends Exception {

    /**
     * Constructor
     */
    public InvalidInputException() {
        super();
    }

    /**
     * Get the message generated for this exception
     *
     * @return the message
     */
    @Override
    public String getMessage() {
        return "Invalid input given.";
    }
}
