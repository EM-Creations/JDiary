package com.emcreations.courseworkup608985.exception;

import com.emcreations.courseworkup608985.entity.Client;

/**
 * UserIncorrectPasswordException
 * 
 * @author Edward McKnight (UP608985)
 */
public class UserIncorrectPasswordException extends UserException {
    private final Client client;
    
    /**
     * Constructor
     * 
     * @param c Client
     */
    public UserIncorrectPasswordException(Client c) {
        super();
        this.client = c;
    }
    
    /**
     * Get message
     * 
     * @return String
     */
    @Override
    public String getMessage() {
        return "Incorrect password provided for username: \"" + this.client.getUsername() + "\".";
    }
}
