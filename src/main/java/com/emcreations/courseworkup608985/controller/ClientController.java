package com.emcreations.courseworkup608985.controller;

import com.emcreations.courseworkup608985.business.ClientService;
import com.emcreations.courseworkup608985.business.ClientService.SearchType;
import com.emcreations.courseworkup608985.entity.Client;
import com.emcreations.courseworkup608985.exception.InvalidSearchTypeException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 * ClientController
 * 
 * @author Edward McKnight (UP608985)
 */
@Named(value = "clientController")
@SessionScoped
public class ClientController implements Serializable {
    private static final long serialVersionUID = 1L;
    @EJB
    private ClientService cS;
    private Client editingClient;
    private List<Client> searchResults;
    private String searchText;
    private String searchTypeText;

    /**
     * Get the value of searchTypeText
     *
     * @return the value of searchTypeText
     */
    public String getSearchTypeText() {
        return searchTypeText;
    }

    /**
     * Set the value of searchTypeText
     *
     * @param searchTypeText String
     */
    public void setSearchTypeText(String searchTypeText) {
        this.searchTypeText = searchTypeText;
    }

    /**
     * Get the value of searchText
     *
     * @return the value of searchText
     */
    public String getSearchText() {
        return searchText;
    }

    /**
     * Set the value of searchText
     *
     * @param searchText new value of searchText
     */
    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
    
    /**
     * Get the value of searchResults
     *
     * @return the value of searchResults
     */
    public List<Client> getSearchResults() {
        return searchResults;
    }

    /**
     * Set the value of searchResults
     *
     * @param searchResults new value of searchResults
     */
    public void setSearchResults(List<Client> searchResults) {
        this.searchResults = searchResults;
    }

    /**
     * Search for clients by search type and search text
     * 
     * @param searchType String
     * @param searchText String
     * @return String
     */
    public String doSearchClient(String searchType, String searchText) {
        // TODO: Validate inputs
        this.setSearchText(searchText);
        this.setSearchTypeText(searchType);
        SearchType sT;
        try {
            sT = this.getSearchTypeFromString(searchType);
        } catch (InvalidSearchTypeException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            sT = SearchType.username; // Default to username if illegal input was given
        }
        this.setSearchResults(cS.searchClient(sT, searchText));
        return ""; // Reload the same page
    }
    
    /**
     * Convert strings from the view to valid SearchType values
     * 
     * @param searchType String
     * @return SearchType
     */
    private SearchType getSearchTypeFromString(String searchType) throws InvalidSearchTypeException {
        switch (searchType) {
            case "username":
                return SearchType.username;
                
            case "firstName":
                return SearchType.firstName;
                
            case "lastName":
                return SearchType.lastName;
                
            case "address":
                return SearchType.address;
                
            case "postcode":
                return SearchType.postcode;
                
            case "phone":
                return SearchType.phone;
                
            case "email":
                return SearchType.email;
            
            default:
                throw new InvalidSearchTypeException(searchType);
        }
    }
    
    /**
     * Load the view to edit a client
     * 
     * @param c Client
     * @return String
     */
    public String goToEditClient(Client c) {
        this.setEditingClient(c);
        return "addEditUser"; // Go to the edit page
    }
    
    /**
     * Load the view to view a client
     * 
     * @param c Client
     * @return String
     */
    public String goToViewClient(Client c) {
        this.setEditingClient(c);
        return "viewUser"; // Go to the view page
    }
    
    /**
     * Delete a client
     * 
     * @param c Client
     * @return String
     */
    public String doDeleteClient(Client c) {
        cS.removeClient(c);
        this.clearEditingClient();
        return ""; // Reload the same page
    }
    
    /**
     * Clear data concerning the client currently being edited
     * 
     * @return Client
     */
    public Client clearEditingClient() {
        this.editingClient = new Client(); // Instantiate new client object (clears any existing data)
        return this.editingClient;
    }
    
    /**
     * Process adding a client
     * 
     * @return String
     */
    public String doAddClient() {
        // TODO check client doesn't already exist
        // TODO validation
        cS.createClient(this.editingClient);
        this.clearEditingClient(); // Reset the client
        return "users"; // Load the users page
    }
    
    /**
     * Processing editing a client
     * 
     * @return String
     */
    public String doEditClient() {
        // TODO validation
        cS.editClient(this.editingClient);
        this.clearEditingClient();
        return "users"; // Go back to the users page
    }

    /**
     * Get the value of editingClient
     *
     * @return the value of editingClient
     */
    public Client getEditingClient() {
        return this.editingClient;
    }

    /**
     * Set the value of editingClient
     *
     * @param editingClient new value of editingClient
     */
    public void setEditingClient(Client editingClient) {
        this.editingClient = editingClient;
    }
    
    /**
     * Creates a new instance of ClientController
     */
    public ClientController() {
        this.editingClient = new Client(); // Instantiate new client object
    }
    
    /**
     * Get all clients
     * 
     * @return List
     */
    public List<Client> getAllClients() {
        return cS.getAll();
    }
    
}
