package mcknighte.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mcknighte.business.ClientService;
import mcknighte.entity.Client;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import mcknighte.common.AbstractController;
import mcknighte.common.AbstractFacade;
import mcknighte.exception.UserDoesNotExistException;

/**
 * LoginController, controller class to handle logging into the application,
 * some navigation and keeping track of the logged in client
 *
 * @author Edward McKnight (UP608985)
 * @see AbstractController
 * @since 2017
 * @version 1.0
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController extends AbstractController {

    private static final long serialVersionUID = 1L;
    @EJB
    private ClientService cS;
    private Client loggedInClient;
    private String userName;
    private String password;

    /**
     * Get the currently logged in client
     *
     * @return the currently logged in client
     */
    public Client getLoggedInClient() {
        return this.loggedInClient;
    }

    /**
     * Get the username being used to login with
     *
     * @return the username being used to login with
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set the username to use when logging in
     *
     * @param userName the username to use to login with
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get the password being used to login with
     *
     * @return the password being used to login with
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password to use when logging in
     *
     * @param password the password to use to login with
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Handle the login form being submitted, check that the user has the
     * correct username and password and if not handle it appropriately
     *
     * @return the view to display
     */
    public String doLogin() {
        Client c;
        try {
            c = cS.checkLogin(this.userName, this.password);

            if (c != null) { // If the login was successful
                this.loggedInClient = c;
                this.addInfo("infoMsg", "Logged in", "Successfully logged in");
                return "welcome?faces-redirect=true";
            } else { // If the login was unsuccessful
                this.addError("loginForm:userName", "Failed login", "Incorrect username / password");
            }
        } catch (UserDoesNotExistException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            this.addError("loginForm:userName", "Failed login", "Incorrect username / password");
        }
        return "index";
    }
    
    /**
     * Handle the login logout button being clicked
     *
     * @return the view to display
     */
    public String doLogout() {
        this.loggedInClient = null; // Set the logged in client to null (logged out state)
        this.addInfo("infoMsg", "Logged out", "Successfully logged out");
        return "index"; // Redirect them back to login
    }

    /**
     * The check performed on every "private" page of the application, to ensure
     * that a user is logged in being seeing those views
     * 
     * @throws IOException if an input/output error occurs
     */
    public void viewLoginCheck() throws IOException {
        if (this.loggedInClient == null) { // If the user isn't logged in
            ExternalContext eC = FacesContext.getCurrentInstance().getExternalContext();
            eC.redirect(eC.getRequestContextPath() + "/faces/index.xhtml");
        }
    }

    /**
     * Constructor
     */
    public LoginController() {
        super(null); // This controller doesn't have a corresponding entity
        this.userName = "";
        this.password = "";
    }

    /**
     * Get the corresponding facade object for this controller
     *
     * @return the corresponding facade object for this controller
     */
    @Override
    public AbstractFacade getFacade() {
        return null; // This controller doesn't have a corresponding facade
    }

}
