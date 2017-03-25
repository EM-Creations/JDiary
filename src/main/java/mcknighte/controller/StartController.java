package mcknighte.controller;

import java.util.ArrayList;
import java.util.Calendar;
import mcknighte.business.ClientService;
import mcknighte.entity.Client;
import mcknighte.exception.UserAlreadyExistsException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import mcknighte.business.AppointmentService;
import mcknighte.common.AbstractController;
import mcknighte.common.AbstractFacade;
import mcknighte.entity.Appointment;
import mcknighte.exception.AppointmentClashException;
import mcknighte.exception.AppointmentEndBeforeStartException;

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
    @EJB
    private AppointmentService aS;

    /**
     * Run the setup, creating all necessary database entries in order to start
     * using the application
     *
     * @return view to display
     */
    public String doSetup() {
        if (!cS.clientExists("adminadmin")) { // If the setup hasn't been run before
            // Create clients
            Client c = new Client();
            c.setUsername("adminadmin");
            c.setPassword("adminadmin");
            c.setFirstName("admin");
            c.setLastName("admin");
            c.setAddress("Admin Lane");
            c.setCountry("United Kingdom");
            c.setPostcode("PO1 2UP");
            c.setPhone("07522631771");
            c.setEmail("admin@admin.com");
            
            Client c2 = new Client();
            c2.setUsername("EddyMcK");
            c2.setPassword("EddyMcK");
            c2.setFirstName("Edward");
            c2.setLastName("McKnight");
            c2.setAddress("Eddy Lane");
            c2.setCountry("United Kingdom");
            c2.setPostcode("PO1 2UP");
            c2.setPhone("023 9284 8484");
            c2.setEmail("eddy@em-creations.co.uk");
            
            Client c3 = new Client();
            c3.setUsername("JimBriggs");
            c3.setPassword("JimBriggs");
            c3.setFirstName("Jim");
            c3.setLastName("Briggs");
            c3.setAddress("University House, Winston Churchill Ave, Portsmouth");
            c3.setCountry("United Kingdom");
            c3.setPostcode("PO1 2UP");
            c3.setPhone("023 9284 8484");
            c3.setEmail("jim.briggs@website.com");
            
            Client c4 = new Client();
            c4.setUsername("JohnDoe");
            c4.setPassword("JohnDoe");
            c4.setFirstName("John");
            c4.setLastName("Doe");
            c4.setAddress("Doe Street");
            c4.setCountry("Canada");
            c4.setEmail("john.doe@canada.ca");

            try {
                cS.createClient(c);
                cS.createClient(c2);
                cS.createClient(c3);
                cS.createClient(c4);
            } catch (UserAlreadyExistsException ex) {
                Logger.getLogger(StartController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Create appointments
            Appointment a = new Appointment();
            a.setCreator(c2);
            a.setDescription("ENTWA Coursework due");
            ArrayList<Client> attendees1 = new ArrayList<>();
            attendees1.add(c2);
            attendees1.add(c3);
            a.setAttendees(attendees1);
            Calendar start1 = Calendar.getInstance();
            start1.set(Calendar.YEAR, 2017);
            start1.set(Calendar.MONTH, Calendar.MARCH);
            start1.set(Calendar.DAY_OF_MONTH, 27);
            start1.set(Calendar.HOUR_OF_DAY, 17);
            start1.set(Calendar.MINUTE, 0);
            a.setStartTime(start1);
            Calendar end1 = (Calendar) start1.clone();
            end1.set(Calendar.MINUTE, 1);
            a.setEndTime(end1);
            
            Appointment a2 = new Appointment();
            a2.setCreator(c);
            a2.setDescription("Monthly meeting");
            ArrayList<Client> attendees2 = new ArrayList<>();
            attendees2.add(c);
            attendees2.add(c2);
            attendees2.add(c3);
            attendees2.add(c4);
            a2.setAttendees(attendees2);
            Calendar start2 = Calendar.getInstance();
            start2.set(Calendar.YEAR, 2017);
            start2.set(Calendar.MONTH, Calendar.MARCH);
            start2.set(Calendar.DAY_OF_MONTH, 31);
            start2.set(Calendar.HOUR_OF_DAY, 13);
            start2.set(Calendar.MINUTE, 0);
            a2.setStartTime(start2);
            Calendar end2 = (Calendar) start2.clone();
            end2.set(Calendar.HOUR_OF_DAY, 14);
            a2.setEndTime(end2);
            
            Appointment a3 = new Appointment();
            a3.setCreator(c2);
            a3.setDescription("ENTWA peer review");
            ArrayList<Client> attendees3 = new ArrayList<>();
            attendees3.add(c);
            attendees3.add(c2);
            attendees3.add(c3);
            attendees3.add(c4);
            a3.setAttendees(attendees3);
            Calendar start3 = Calendar.getInstance();
            start3.set(Calendar.YEAR, 2017);
            start3.set(Calendar.MONTH, Calendar.MARCH);
            start3.set(Calendar.DAY_OF_MONTH, 31);
            start3.set(Calendar.HOUR_OF_DAY, 10);
            start3.set(Calendar.MINUTE, 0);
            a3.setStartTime(start3);
            Calendar end3 = (Calendar) start3.clone();
            end3.set(Calendar.HOUR_OF_DAY, 12);
            a3.setEndTime(end3);
            
            try {
                aS.createAppointment(a);
                aS.createAppointment(a2);
                aS.createAppointment(a3);
            } catch (AppointmentClashException | AppointmentEndBeforeStartException ex) {
                Logger.getLogger(StartController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            this.addInfo("infoMsg", "Setup completed");
            return "start"; // Go back to the start view
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
