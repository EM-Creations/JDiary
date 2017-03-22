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
 */
@Stateless
public class ClientService {
    @EJB
    private ClientFacade cF;
    @EJB
    private AppointmentFacade aF;

    public enum SearchType {
        all, username, firstName, lastName, address, postcode, phone, email
    };

    /**
     * Get a list of country names
     *
     * @return List
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
     * @param searchType SearchType
     * @param searchText String
     * @return List
     */
    public List<Client> searchClient(SearchType searchType, String searchText) {
        return cF.search(searchType, searchText);
    }

    /**
     * Edit client
     *
     * @param client Client
     * @return Client
     */
    public Client editClient(Client client) {
        cF.edit(client);
        return client;
    }

    /**
     * Edit client, requiring a password
     *
     * @param client Client
     * @param providedPassword String
     * @return Client
     * @throws mcknighte.exception.UserIncorrectPasswordException
     * @throws mcknighte.exception.UserDoesNotExistException
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
     * Get client
     *
     * @param client Client
     * @return Client
     */
    public Client getClient(Client client) {
        return cF.find(client.getId());
    }

    /**
     * Get a client by their username
     *
     * @param userName String
     * @return Client
     */
    public Client getClient(String userName) {
        return cF.find(userName);
    }

    /**
     * Create client
     *
     * @param client Client
     * @return Client
     * @throws mcknighte.exception.UserAlreadyExistsException
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
     * Remove client
     *
     * @param client Client
     * @return Client
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
     * @param userName String
     * @return boolean
     */
    public boolean clientExists(String userName) {
        return cF.find(userName) != null;
    }

    /**
     * Check whether a client should be able to login with the provided
     * credentials, returns null if failed login
     *
     * @param userName String
     * @param password String
     * @return Client
     * @throws mcknighte.exception.UserDoesNotExistException
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
     * Get all clients
     *
     * @return List
     */
    public List<Client> getAll() {
        return cF.findAll();
    }
}
