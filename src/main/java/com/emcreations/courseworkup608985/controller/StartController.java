package com.emcreations.courseworkup608985.controller;

import com.emcreations.courseworkup608985.business.ClientService;
import com.emcreations.courseworkup608985.entity.Client;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 * StartController
 * 
 * @author Edward McKnight (UP608985)
 */
@Named(value = "startController")
@RequestScoped
public class StartController {
    @EJB
    private ClientService cs;

    /**
     * Run the setup
     * 
     * @return String 
     */
    public String doSetup() {
        // Check that the admin user doesn't already exist
        if (!cs.clientExists("admin")) { // If the admin user doesn't exist
            // Create client(s)
            Client c = new Client();
            c.setUsername("admin");
            c.setPassword("admin");
            c.setFirstName("admin");
            c.setLastName("admin");
            c.setAddress("admin");
            c.setPostcode("admin");
            c.setPhone("123456789");
            c.setEmail("admin@admin.com");

            cs.createClient(c); // Persist this object
        }
        
        return "start"; // Go back to the start view
    }
    
    /**
     * Home button pressed
     * 
     * @return String
     */
    public String goHome() {
        return "index";
    }
    
    /**
     * Creates a new instance of StartController
     */
    public StartController() {
    }
    
}
