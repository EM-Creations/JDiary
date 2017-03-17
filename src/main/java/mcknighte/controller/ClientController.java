package mcknighte.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import mcknighte.entity.Client;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import mcknighte.business.ClientService;
import mcknighte.business.ClientService.SearchType;
import mcknighte.common.AbstractController;
import mcknighte.exception.InvalidSearchTypeException;
import mcknighte.exception.UserAlreadyExistsException;
import mcknighte.exception.UserIncorrectPasswordException;
import mcknighte.persistence.ClientFacade;

/**
 * ClientController
 *
 * @author Edward McKnight (UP608985)
 */
@Named(value = "clientController")
@SessionScoped
public class ClientController extends AbstractController<Client, ClientFacade> {
    private static final long serialVersionUID = 1L;
    @EJB
    private ClientService cS;
    @EJB
    private ClientFacade cF;
    private Client editingClient;
    private List<Client> searchResults;
    private String searchText;
    private String searchTypeText;

    /**
     * Check whether a client exists and output a Faces message if it does to the specified element
     * 
     * @param userName String
     * @param message boolean
     * @param element String
     * @return boolean
     */
    public boolean clientExists(String userName, boolean message, String element) {
        if ((this.editingClient.getId() == null && cS.clientExists(userName)) || (cS.clientExists(userName) && (!cS.getClient(userName).equals(this.editingClient)))) { // If the client already exists
            if (message) { // If a message is being output
                this.addError(element, "Already exists", "A user with this username already exists.");
            }
            return true;
        }
        return false; // Otherwise the client doesn't exist
    }

    /**
     * Get the value of countries
     *
     * @return List
     */
    public List<String> getCountries() {
        return cS.getCountries();
    }

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
            sT = SearchType.all; // Default to all if illegal input was given
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
            case "all":
                return SearchType.all;
            
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
        // TODO: Refresh the view properly
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
     * @param passwordAgain String
     * @return String
     */
    public String doAddClient(String passwordAgain) {
        // TODO validation
        if (!this.editingClient.getPassword().equals(passwordAgain)) { // If the password doesn't match the retype of the password
            this.addError("newUserForm:passwordAgain", "Mismatch", "Passwords do not match.");
            return "addEditUser"; // Send them back to the form
        }
        
        try {
            cS.createClient(this.editingClient);
        } catch (UserAlreadyExistsException ex) { // If the user already exists
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            this.addError("newUserForm:userName", "Already exists", "A user with this username already exists.");
            return "addEditUser"; // Reload the page
        }
        this.clearEditingClient(); // Reset the client
        return "users"; // Load the users page
    }

    /**
     * Processing editing a client
     *
     * @param currPassword String
     * @param newPassword String
     * @param passwordAgain String
     * @return String
     */
    public String doEditClient(String currPassword, String newPassword, String passwordAgain) {
        // TODO validation
        if (!newPassword.equals("")) { // If the user opted to change the password
            if (!newPassword.equals(passwordAgain)) { // If the new password doesn't match the retype of the password
                this.addError("newUserForm:passwordAgain", "Mismatch", "Passwords do not match.");
                return "addEditUser"; // Send them back to the form
            }
            this.editingClient.setPassword(newPassword); // Set the new password
        }
        
        try {
            cS.editClient(this.editingClient, currPassword);
        } catch (UserIncorrectPasswordException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            this.addError("newUserForm:currPassword", "Incorrect", "Incorrect password given.");
            return "addEditUser";
        }
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
        super(Client.class);
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

    /**
     * Get the facade for this object
     * 
     * @return AbstractFacade
     */
    @Override
    public ClientFacade getFacade() {
        return cF;
    }

}
