package mcknighte.exception;

/**
 * InvalidSearchTypeException, exception thrown for when an invalid 
 * search type is provided
 *
 * @author Edward McKnight (UP608985)
 * @see InvalidInputException
 * @since 2017
 * @version 1.0
 */
public class InvalidSearchTypeException extends InvalidInputException {
    private final String inputProvided;

    /**
     * Constructor
     *
     * @param inputProvided the input which has been provided
     */
    public InvalidSearchTypeException(String inputProvided) {
        super();
        this.inputProvided = inputProvided;
    }

    /**
     * Get the message generated for this exception
     *
     * @return the message
     */
    @Override
    public String getMessage() {
        return "The given search type text: \"" + this.inputProvided + "\" could not be converted to a corresponding SearchType enum.";
    }
}
