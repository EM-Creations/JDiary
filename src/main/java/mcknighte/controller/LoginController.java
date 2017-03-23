package mcknighte.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import mcknighte.business.ClientService;
import mcknighte.entity.Client;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
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
    private ClientService cs;
    private Client loggedInClient = new Client();
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
            c = cs.checkLogin(this.userName, this.password);

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
     * Constructor
     */
    public LoginController() {
        super(null); // This controller doesn't have a corresponding entity
        this.userName = "";
        this.password = "adminadmin"; // TODO: DEVELOPMENT PURPOSES ONLY (pre-set the password for admin user)
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
