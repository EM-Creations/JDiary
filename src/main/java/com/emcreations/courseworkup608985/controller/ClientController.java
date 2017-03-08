package com.emcreations.courseworkup608985.controller;

import com.emcreations.courseworkup608985.business.ClientService;
import com.emcreations.courseworkup608985.entity.Client;
import java.io.Serializable;
import java.util.List;
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
     */
    private void clearEditingClient() {
        this.editingClient = new Client(); // Instantiate new client object (clears any existing data)
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
     * @return List<Client>
     */
    public List<Client> getAllClients() {
        return cS.getAll();
    }
    
}
