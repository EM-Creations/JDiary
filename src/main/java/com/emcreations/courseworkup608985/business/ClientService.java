package com.emcreations.courseworkup608985.business;

import com.emcreations.courseworkup608985.entity.Appointment;
import com.emcreations.courseworkup608985.persistence.AppointmentFacade;
import com.emcreations.courseworkup608985.entity.Client;
import com.emcreations.courseworkup608985.persistence.ClientFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Edward McKnight (UP608985)
 */
@Stateless
public class ClientService {
    @EJB
    private ClientFacade cF;
    @EJB
    private AppointmentFacade aF;
    public enum SearchType {username, firstName, lastName, address, postcode, phone, email};

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
     */
    public Client createClient(Client client) {
        cF.create(client);
        return client;
    }

    /**
     * Remove client
     * 
     * @param client Client
     * @return Client
     */
    public Client removeClient(Client client) {
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
     * Check whether a client should be able to login with the provided credentials, returns null if failed login
     * 
     * @param userName String
     * @param password String
     * @return Client
     */
    public Client checkLogin(String userName, String password) {
        return cF.find(userName, password); // Return whether we can find a client with that username and password or not
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
