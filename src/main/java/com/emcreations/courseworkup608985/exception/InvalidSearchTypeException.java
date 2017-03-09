package com.emcreations.courseworkup608985.exception;

/**
 * InvalidSearchTypeException
 * 
 * @author Edward McKnight (UP608985)
 */
public class InvalidSearchTypeException extends InvalidInputException {
    private final String inputProvided;
    
    /**
     * Constructor
     * 
     * @param inputProvided String
     */
    public InvalidSearchTypeException(String inputProvided) {
        super();
        this.inputProvided = inputProvided;
    }
    
    /**
     * Get message
     * 
     * @return String
     */
    @Override
    public String getMessage() {
        return "The given search type text: \"" + this.inputProvided + "\" could not be converted to a corresponding SearchType enum.";
    }
}
