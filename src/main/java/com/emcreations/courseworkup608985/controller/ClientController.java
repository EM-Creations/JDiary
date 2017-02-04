package com.emcreations.courseworkup608985.controller;

import com.emcreations.courseworkup608985.business.ClientService;
import com.emcreations.courseworkup608985.entity.Client;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Edward McKnight (UP608985)
 */
@Named(value = "clientController")
@RequestScoped
public class ClientController {
    @EJB
    private ClientService cS;
    private Client client;
    
    /**
     * Creates a new instance of UserController
     */
    public ClientController() {
    }
    
    /**
     * Get all users
     * 
     * @return List<Client>
     */
    public List<Client> getAllUsers() {
        return cS.getAll();
    }
    
}
