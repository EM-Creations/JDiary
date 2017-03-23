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
import mcknighte.exception.UserException;
import mcknighte.persistence.ClientFacade;

/**
 * ClientController, controller class to act as middle man between Client views and the business logic
 *
 * @author Edward McKnight (UP608985)
 * @see AbstractController
 * @see AppointmentController
 * @since 2017
 * @version 1.0
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
     * Check whether a client exists and output a Faces message if it does to
     * the specified element
     *
     * @param userName the username of the client to check whether exists
     * @param message whether or not to display an error message if the client doesn't exist
     * @param element the UIComponent to display the error message on (if being used)
     * @return whether or not the client exists
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
     * Get the list of countries from the business logic
     *
     * @return a list of ISO countries
     */
    public List<String> getCountries() {
        return cS.getCountries();
    }

    /**
     * Get the current search type textual value
     *
     * @return the textual value of the search type
     */
    public String getSearchTypeText() {
        return searchTypeText;
    }

    /**
     * Set the text value of the search type
     *
     * @param searchTypeText the text value of the search type
     */
    public void setSearchTypeText(String searchTypeText) {
        this.searchTypeText = searchTypeText;
    }

    /**
     * Get the search text
     *
     * @return the search text
     */
    public String getSearchText() {
        return searchText;
    }

    /**
     * Set the search text to be used
     *
     * @param searchText the search text to be used
     */
    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    /**
     * Get the list of search results
     *
     * @return the list of search results
     */
    public List<Client> getSearchResults() {
        return searchResults;
    }

    /**
     * Set the search results
     *
     * @param searchResults a list of clients resulting from a search
     */
    public void setSearchResults(List<Client> searchResults) {
        this.searchResults = searchResults;
    }

    /**
     * Search for clients by search type and search text
     *
     * @param searchType the type of search being performed
     * @param searchText the text to search by
     * @return the view displayed
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
     * Convert strings from the view to valid enumerated SearchType values
     *
     * @param searchType the string representation of a SearchType
     * @return the corresponding enumerated SearchType value
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
     * @param c the client to edit
     * @return the view displayed
     */
    public String goToEditClient(Client c) {
        this.setEditingClient(c);
        return "addEditUser"; // Go to the edit page
    }

    /**
     * Load the view to view a client
     *
     * @param c the client to view
     * @return the view displayed
     */
    public String goToViewClient(Client c) {
        this.setEditingClient(c);
        return "viewUser"; // Go to the view page
    }

    /**
     * Delete a client
     *
     * @param c the client to delete
     * @return the view displayed
     */
    public String doDeleteClient(Client c) {
        cS.removeClient(c);
        this.clearEditingClient();
        this.doSearchClient(this.searchTypeText, this.searchText);
        return ""; // Reload the same page
    }

    /**
     * Clear data concerning the client currently being edited
     *
     * @return the new editing client
     */
    public Client clearEditingClient() {
        this.editingClient = new Client(); // Instantiate new client object (clears any existing data)
        return this.editingClient;
    }

    /**
     * Process adding a client, checking that the new password has been correctly
     * entered twice and that the username isn't already taken
     *
     * @param password the password for the new client
     * @param passwordAgain the repeated password for the new client
     * @return the view displayed
     */
    public String doAddClient(String password, String passwordAgain) {
        // TODO validation
        if (!password.equals(passwordAgain)) { // If the password doesn't match the retype of the password
            this.addError("newUserForm:passwordAgain", "Mismatch", "Passwords do not match.");
            return "addEditUser"; // Send them back to the form
        }
        this.editingClient.setPassword(password); // Set the new password

        try {
            cS.createClient(this.editingClient);
        } catch (UserAlreadyExistsException ex) { // If the user already exists
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            this.addError("newUserForm:userName", "Already exists", "A user with this username already exists.");
            return "addEditUser"; // Reload the page
        }
        this.addInfo("infoMsg", "User added", "New user added");
        this.clearEditingClient(); // Reset the client
        return "users?faces-redirect=true"; // Load the users page
    }

    /**
     * Processing editing a client, ensuring that the new password (if used)
     * has been correctly entered twice
     *
     * @param currPassword the client's current password
     * @param newPassword the client's new password
     * @param passwordAgain the repeated client's new password
     * @return the view displayed
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
        } catch (UserException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            this.addError("newUserForm:currPassword", "Incorrect", "Incorrect password given.");
            return "addEditUser";
        }
        this.addInfo("infoMsg", "User edited", "User edited");
        this.clearEditingClient();
        return "users?faces-redirect=true"; // Go back to the users page
    }

    /**
     * Get the client which is currently being edited
     *
     * @return the client which is currently being edited
     */
    public Client getEditingClient() {
        return this.editingClient;
    }

    /**
     * Set the client which is currently being edited
     *
     * @param editingClient the client which is currently being edited
     */
    public void setEditingClient(Client editingClient) {
        this.editingClient = editingClient;
    }

    /**
     * Constructor
     */
    public ClientController() {
        super(Client.class);
        this.editingClient = new Client(); // Instantiate new client object
    }

    /**
     * Get all clients held by the system from the business logic
     *
     * @return a list of all clients held by the system
     */
    public List<Client> getAllClients() {
        return cS.getAll();
    }

    /**
     * Get the corresponding facade object for this controller
     *
     * @return the corresponding facade object for this controller
     */
    @Override
    public ClientFacade getFacade() {
        return cF;
    }

}
