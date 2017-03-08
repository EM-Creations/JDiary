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
     * Clear data concerning the client currently being edited
     */
    private void clearEditingClient() {
        this.editingClient = new Client(); // Instantiate new client object (clears any existing data)
    }
    
    /**
     * Process adding or editing a client
     * 
     * @return String
     */
    public String doAddEditClient() {
        // TODO check client doesn't already exist
        cS.createClient(this.editingClient);
        this.clearEditingClient(); // Reset the client
        return "users"; // Load the users page
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
        
        // THIS WORKS THE PROBLEM IS IN GETTING THE DATA OUT OF THE DATABASE
        Client c2 = new Client();
        c2.setUsername("tester");
        
        //Client c2 = cF.find("admin");
        this.setEditingClient(c2);
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
