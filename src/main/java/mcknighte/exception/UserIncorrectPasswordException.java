package mcknighte.exception;

import mcknighte.entity.Client;

/**
 * UserIncorrectPasswordException
 *
 * @author Edward McKnight (UP608985)
 * @see UserException
 * @since 2017
 * @version 1.0
 */
public class UserIncorrectPasswordException extends UserException {
    private final Client client;

    /**
     * Constructor
     *
     * @param c the client who provided an incorrect password
     */
    public UserIncorrectPasswordException(Client c) {
        super();
        this.client = c;
    }

    /**
     * Get the message generated for this exception
     *
     * @return the message
     */
    @Override
    public String getMessage() {
        return "Incorrect password provided for username: \"" + this.client.getUsername() + "\".";
    }
}
