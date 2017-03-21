package mcknighte.controller;

import mcknighte.business.ClientService;
import mcknighte.entity.Client;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import mcknighte.common.AbstractController;
import mcknighte.common.AbstractFacade;

/**
 * LoginController
 * 
 * @author Edward McKnight (UP608985)
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
     * Login button pressed
     * 
     * @return String
     */
    public String doLogin() {
        Client c = cs.checkLogin(this.userName, this.password);
        
        if (c != null) { // If the login was successful
            this.loggedInClient = c;
            this.addInfo("infoMsg", "Logged in", "Successfully logged in");
            return "welcome?faces-redirect=true";
        } else { // If the login was unsuccessful
            this.addError("loginForm:userName", "Failed login", "Incorrect username / password");
        }
        return "index";
    }
    
    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
        super(null); // This controller doesn't have a corresponding entity
        this.userName = "";
        this.password = "adminadmin"; // TODO: DEVELOPMENT PURPOSES ONLY (pre-set the password for admin user)
    }

    /**
     * Get the facade for this object
     * 
     * @return 
     */
    @Override
    public AbstractFacade getFacade() {
        return null; // This controller doesn't have a corresponding facade
    }
    
}
