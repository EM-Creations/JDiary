package com.emcreations.courseworkup608985.controller;

import com.emcreations.courseworkup608985.business.ClientService;
import com.emcreations.courseworkup608985.entity.Client;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * LoginController
 * 
 * @author Edward McKnight (UP608985)
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {
    private static final long serialVersionUID = 1L;
    @EJB
    private ClientService cs;
    private Client loggedInClient = new Client();
    private String userName;
    private String password;
    
    /**
     * Get the value of loggedInClient
     * 
     * @return Client 
     */
    public Client getLoggedInClient() {
        return this.loggedInClient;
    }

    /**
     * Get the value of userName
     *
     * @return the value of userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set the value of userName
     *
     * @param userName new value of userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Users button pressed
     * 
     * @return String
     */
    public String goUsers() {
        return "users";
    }
    
    /**
     * Add new user button pressed
     * 
     * @return String
     */
    public String goAddUser() {
        return "addEditUser";
    }
    
    /**
     * Browse users button pressed
     * 
     * @return String
     */
    public String goBrowseUsers() {
        return "browseUsers";
    }

    /**
     * Login button pressed
     * 
     * @return String
     */
    public String doLogin() {
        Client c = cs.checkLogin(this.userName, this.password);
        
        if (c != null) { // If the login was successful
            this.loggedInClient = c;
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Logged in",
                    "Welcome " + this.loggedInClient.getFirstName() + "!"));
            return "welcome";
        } else { // If the login was unsuccessful
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Failed login",
                    "Incorrect username / password"));
        }
        return "index";
    }
    
    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
        this.userName = "";
        this.password = "admin"; // DEVELOPMENT PURPOSES ONLY (pre-set the password for admin user)
    }
    
}
