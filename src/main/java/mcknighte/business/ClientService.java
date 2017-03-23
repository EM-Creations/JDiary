package mcknighte.business;

import java.util.ArrayList;
import java.util.Collections;
import mcknighte.persistence.AppointmentFacade;
import mcknighte.entity.Client;
import mcknighte.exception.UserAlreadyExistsException;
import mcknighte.exception.UserIncorrectPasswordException;
import mcknighte.persistence.ClientFacade;
import java.util.List;
import java.util.Locale;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import mcknighte.entity.Appointment;
import mcknighte.exception.UserDoesNotExistException;

/**
 * ClientService
 *
 * @author Edward McKnight (UP608985)
 * @see AppointmentService
 * @since 2017
 * @version 1.0
 */
@Stateless
public class ClientService {
    @EJB
    private ClientFacade cF;
    @EJB
    private AppointmentFacade aF;

    /**
     * An enumerated type representing all of the search methods available for clients
     */
    public enum SearchType {
        all, username, firstName, lastName, address, postcode, phone, email
    };

    /**
     * Get an alphabetically sorted list of all ISO country names
     *
     * @return a list of all country names
     */
    public List<String> getCountries() {
        // mkyong. (2013). Display a list of countries in Java. Retrieved from http://www.mkyong.com/java/display-a-list-of-countries-in-java/
        ArrayList<String> countries = new ArrayList<>();
        String[] countryCodes = Locale.getISOCountries();

        for (String countryCode : countryCodes) { // For each country code
            Locale locale = new Locale("", countryCode);
            countries.add(locale.getDisplayCountry()); // Add this country's display name to the ArrayList
        }

        Collections.sort(countries); // Sort the list of countries alphabetically

        return countries; // Return the ArrayList of display country names
    }

    /**
     * Search for clients based on a search type and search text
     *
     * @param searchType the type of search to perform
     * @param searchText the search input to be used
     * @return a list of clients returned by the search; if no results null
     */
    public List<Client> searchClient(SearchType searchType, String searchText) {
        return cF.search(searchType, searchText);
    }

    /**
     * Edit a client
     *
     * @param client the client to edit
     * @return the client which was edited
     */
    public Client editClient(Client client) {
        cF.edit(client);
        return client;
    }

    /**
     * Edit a client, requiring a password
     *
     * @param client the client to edit
     * @param providedPassword the password to use
     * @return the client which was edited
     * @throws mcknighte.exception.UserIncorrectPasswordException if the password provided is incorrect
     * @throws mcknighte.exception.UserDoesNotExistException if the user/client does not exist
     */
    public Client editClient(Client client, String providedPassword) throws UserIncorrectPasswordException, UserDoesNotExistException {
        if (this.checkLogin(client.getUsername(), providedPassword) != null) { // If the correct password has been provided
            cF.edit(client);
        } else { // If the incorrect password has been provided
            throw new UserIncorrectPasswordException(client);
        }
        return client;
    }

    /**
     * Get a client from a Client object
     *
     * @param client the client to get
     * @return the found client; otherwise null
     */
    public Client getClient(Client client) {
        return cF.find(client.getId());
    }

    /**
     * Get a client by their username
     *
     * @param userName the username of the client to get
     * @return the found client; otherwise null
     */
    public Client getClient(String userName) {
        return cF.find(userName);
    }

    /**
     * Create a client
     *
     * @param client the client to create
     * @return the client which has been created
     * @throws mcknighte.exception.UserAlreadyExistsException if a client with that username already exists
     */
    public Client createClient(Client client) throws UserAlreadyExistsException {
        if (!this.clientExists(client.getUsername())) { // If this client doesn't already exist
            cF.create(client);
        } else { // If this client does exist
            throw new UserAlreadyExistsException(client.getUsername());
        }
        return client;
    }

    /**
     * Remove a client, ensuring that they have also been removed
     * from their relevant appointments
     *
     * @param client the client to remove
     * @return the client which has been removed
     */
    public Client removeClient(Client client) {
        // Remove this client from any appointments that they may be attending
        List<Appointment> appointments = aF.search(client);
        if (appointments != null) { // If this client is an attendee of at least 1 appointment
            for (Appointment a : appointments) { // For each appointment that this client is attending
                List<Client> newAttendees = a.getAttendees();
                newAttendees.remove(client); // Remove the client from this appointment
                a.setAttendees(newAttendees); // Set the new list of attendees
                aF.edit(a); // Update the appointment
            }
        }

        cF.remove(client);
        return client;
    }

    /**
     * Check whether a client exists or not by username
     *
     * @param userName the client's username to check for
     * @return whether or not the client exists
     */
    public boolean clientExists(String userName) {
        return cF.find(userName) != null;
    }

    /**
     * Check whether a client should be able to login with the provided
     * credentials, returns null if failed login
     *
     * @param userName the client's username to check for
     * @param password the client's password to try to login with
     * @return the corresponding client; if incorrect password null
     * @throws mcknighte.exception.UserDoesNotExistException if the client/user does not exist
     */
    public Client checkLogin(String userName, String password) throws UserDoesNotExistException {
        Client c = cF.find(userName); // First get the client with their username

        if (c != null) { // If a client with that username has been found
            return cF.find(userName, password, c.getSalt()); // Return whether we can find a client with that username and password or not
        }
        // Otherwise
        throw new UserDoesNotExistException(userName);
    }

    /**
     * Get all clients held by the system
     *
     * @return a list of all clients held by the system
     */
    public List<Client> getAll() {
        return cF.findAll();
    }
}
