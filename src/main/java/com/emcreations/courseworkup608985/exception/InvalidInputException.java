package com.emcreations.courseworkup608985.exception;

/**
 * InvalidInputException
 * 
 * @author Edward McKnight (UP608985)
 */
public abstract class InvalidInputException extends Exception {
    
    /**
     * Constructor
     */
    public InvalidInputException() {
        super();
    }
    
    /**
     * Get message
     * 
     * @return String
     */
    @Override
    public String getMessage() {
        return "Invalid input given";
    }
}
