package mcknighte.controller;

import mcknighte.business.ClientService;
import mcknighte.entity.Client;
import mcknighte.exception.UserAlreadyExistsException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import mcknighte.common.AbstractController;
import mcknighte.common.AbstractFacade;

/**
 * StartController, used to handle navigation and the setup feature (which will
 * create all necessary data to run the application while in development)
 *
 * @author Edward McKnight (UP608985)
 * @since 2017
 * @version 1.0
 */
@Named(value = "startController")
@RequestScoped
public class StartController extends AbstractController {
    @EJB
    private ClientService cS;

    /**
     * Run the setup, creating all necessary database entries in order to start
     * using the application
     *
     * @return view to display
     */
    public String doSetup() {
        if (!cS.clientExists("adminadmin")) { // If the setup hasn't been run before
            // Create client(s)
            Client c = new Client();
            c.setUsername("adminadmin");
            c.setPassword("adminadmin");
            c.setFirstName("admin");
            c.setLastName("admin");
            c.setAddress("admin");
            c.setCountry("United Kingdom");
            c.setPostcode("PO2 1PL");
            c.setPhone("07522631771");
            c.setEmail("admin@admin.com");

            try {
                cS.createClient(c); // Persist this object
            } catch (UserAlreadyExistsException ex) {
                Logger.getLogger(StartController.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.addInfo("infoMsg", "Setup completed");
        }
        this.addInfo("infoMsg", "Setup already run");
        return "start"; // Go back to the start view
    }

    /**
     * Home button pressed
     *
     * @return view to display
     */
    public String goToHome() {
        return "index";
    }

    /**
     * Users button pressed
     *
     * @return view to display
     */
    public String goToUsers() {
        return "user/users";
    }

    /**
     * Add new user button pressed
     *
     * @return view to display
     */
    public String goToAddUser() {
        return "addEditUser";
    }

    /**
     * Browse users button pressed
     *
     * @return view to display
     */
    public String goToBrowseUsers() {
        return "browseUsers";
    }

    /**
     * Appointments button pressed
     *
     * @return view to display
     */
    public String goToAppointments() {
        return "appointment/appointments";
    }

    /**
     * Create appointment button pressed
     *
     * @return view to display
     */
    public String goToCreateAppointment() {
        return "createEditAppointment";
    }

    /**
     * Browse appointments button pressed
     *
     * @return view to display
     */
    public String goToBrowseAppointments() {
        return "browseAppointments";
    }

    /**
     * Appointments by user button pressed
     *
     * @return view to display
     */
    public String goToAppointmentsByUser() {
        return "appointmentsByUser";
    }

    /**
     * Appointments by day button pressed
     *
     * @return view to display
     */
    public String goToAppointmentsByDay() {
        return "appointmentsByDay";
    }

    /**
     * Appointments calendar button pressed
     *
     * @return view to display
     */
    public String goToAppointmentsCalendar() {
        return "appointmentsCalendar";
    }

    /**
     * Constructor
     */
    public StartController() {
        super(null);
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
