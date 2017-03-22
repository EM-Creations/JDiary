package mcknighte.controller;

import mcknighte.business.ClientService;
import mcknighte.entity.Client;
import mcknighte.exception.UserAlreadyExistsException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        // Create client(s)
        Client c = new Client();
        c.setUsername("adminadmin");
        c.setPassword("adminadmin");
        c.setFirstName("admin");
        c.setLastName("admin");
        c.setAddress("admin");
        c.setCountry("United Kingdom");
        c.setPostcode("PO2 1PL");
        c.setPhone("07530510271");
        c.setEmail("admin@admin.com");

        try {
            cs.createClient(c); // Persist this object
        } catch (UserAlreadyExistsException ex) {
            Logger.getLogger(StartController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "start"; // Go back to the start view
    }

    /**
     * Home button pressed
     *
     * @return String
     */
    public String goToHome() {
        return "index";
    }

    /**
     * Users button pressed
     *
     * @return String
     */
    public String goToUsers() {
        return "user/users";
    }

    /**
     * Add new user button pressed
     *
     * @return String
     */
    public String goToAddUser() {
        return "addEditUser";
    }

    /**
     * Browse users button pressed
     *
     * @return String
     */
    public String goToBrowseUsers() {
        return "browseUsers";
    }
    
    /**
     * Appointments button pressed
     *
     * @return String
     */
    public String goToAppointments() {
        return "appointment/appointments";
    }
    
    /**
     * Create appointment button pressed
     *
     * @return String
     */
    public String goToCreateAppointment() {
        return "createEditAppointment";
    }
    
    /**
     * Browse appointments button pressed
     *
     * @return String
     */
    public String goToBrowseAppointments() {
        return "browseAppointments";
    }
    
    /**
     * Appointments by user button pressed
     *
     * @return String
     */
    public String goToAppointmentsByUser() {
        return "appointmentsByUser";
    }
    
    /**
     * Appointments by day button pressed
     *
     * @return String
     */
    public String goToAppointmentsByDay() {
        return "appointmentsByDay";
    }
    
    /**
     * Appointments calendar button pressed
     *
     * @return String
     */
    public String goToAppointmentsCalendar() {
        return "appointmentsCalendar";
    }

    /**
     * Creates a new instance of StartController
     */
    public StartController() {
    }

}
