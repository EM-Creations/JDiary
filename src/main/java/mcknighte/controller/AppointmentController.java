package mcknighte.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import mcknighte.business.AppointmentService;
import mcknighte.business.ClientService;
import mcknighte.common.AbstractController;
import mcknighte.entity.Appointment;
import mcknighte.entity.Client;
import mcknighte.exception.AppointmentClashException;
import mcknighte.persistence.AppointmentFacade;

/**
 * AppointmentController, controller class to act as middle man between Appointment views and the business logic
 *
 * @author Edward McKnight (UP608985)
 * @see AbstractController
 * @see ClientController
 * @since 2017
 * @version 1.0
 */
@Named(value = "appointmentController")
@SessionScoped
public class AppointmentController extends AbstractController<Appointment, AppointmentFacade> {
    private static final long serialVersionUID = 1L;
    @EJB
    private AppointmentService aS;
    @EJB
    private ClientService cS;
    @EJB
    private AppointmentFacade aF;
    private Appointment editingAppointment;
    private List<String> attendeeUsers;
    private String searchClient;
    private Date searchDay;
    private List<Appointment> searchResults;

    /**
     * Get all of the appointments for a specific day of this current month
     *
     * @param day the day of the month to get appointments for
     * @return a list of the appointments for the specified day of the current month
     */
    public List<Appointment> getAppointmentsForDay(int day) {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        final Date date = calendar.getTime();

        return aS.searchAppointment(date);
    }

    /**
     * Get the current search results
     *
     * @return a list of the search results
     */
    public List<Appointment> getSearchResults() {
        return searchResults;
    }

    /**
     * Set the current search results
     *
     * @param searchResults a list of search results
     */
    public void setSearchResults(List<Appointment> searchResults) {
        this.searchResults = searchResults;
    }

    /**
     * Get current day appointments are being searched for
     *
     * @return the current day being searched for
     */
    public Date getSearchDay() {
        return searchDay;
    }

    /**
     * Set the current day appointments are being searched for
     *
     * @param searchDay a day to search for appointments
     */
    public void setSearchDay(Date searchDay) {
        this.searchDay = searchDay;
    }

    /**
     * Get the username of the client being searched appointments for
     *
     * @return the client being searched appointments for
     */
    public String getSearchClient() {
        return searchClient;
    }

    /**
     * Set the username of the client appointments are being searched for
     *
     * @param searchClient the username of the client appointments are being searched for
     */
    public void setSearchClient(String searchClient) {
        this.searchClient = searchClient;
        this.setSearchResults(aS.searchAppointment(cS.getClient(this.searchClient)));
    }

    /**
     * Get a list of strings of attendees for the current appointment
     *
     * @return a list of strings of attendees for the current appointment
     */
    public List<String> getAttendeeUsers() {
        return attendeeUsers;
    }

    /**
     * Set the string list of attendees for this appointment
     *
     * @param attendeeUsers a string list of attendees for this appointment
     */
    public void setAttendeeUsers(List<String> attendeeUsers) {
        this.attendeeUsers = attendeeUsers;
    }

    /**
     * AppointmentController constructor
     */
    public AppointmentController() {
        super(Appointment.class);
        this.editingAppointment = new Appointment(); // Instantiate new appointment object
        this.attendeeUsers = new ArrayList<>();
    }

    /**
     * Clear data concerning the appointment currently being edited
     *
     * @return the cleared appointment which is currently being edited
     */
    public Appointment clearEditingAppointment() {
        this.editingAppointment = new Appointment(); // Instantiate new appointment object (clears any existing data)
        this.attendeeUsers.clear();
        return this.editingAppointment;
    }

    /**
     * Get appointment which is currently being edited
     *
     * @return the appointment which is currently being edited
     */
    public Appointment getEditingAppointment() {
        return editingAppointment;
    }

    /**
     * Set the currently edited appointment
     *
     * @param editingAppointment an appointment to edit
     */
    public void setEditingAppointment(Appointment editingAppointment) {
        this.editingAppointment = editingAppointment;
        if (this.editingAppointment.getId() != null) {
            this.attendeeUsers = this.convertClientsToClientNames(this.editingAppointment.getAttendees());
        }
    }

    /**
     * Process creating a new appointment, checking that none of the attendees have other appointments during this time
     *
     * @param creator the creator of the appointment
     * @return the view to display
     */
    public String doCreateAppointment(Client creator) {
        // TODO validation
        if (creator == null || creator.getId() == null) { // Temp
            creator = cS.getClient("adminadmin");
        }
        this.editingAppointment.setCreator(creator); // Set the creator for this appointment
        this.editingAppointment.setAttendees(this.convertClientNamesToClients(this.attendeeUsers));

        // Try to create the appointment
        try {
            aS.createAppointment(this.editingAppointment);
        } catch (AppointmentClashException ex) {
            Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
            this.addError("newAppointmentForm:attendees", ex.getMessage());
            return "createEditAppointment"; // Send the user back to the createEditAppointment view
        }
        this.addInfo("infoMsg", "Appointment created", "New appointment created");
        this.clearEditingAppointment(); // Reset the appointment
        return "appointments?faces-redirect=true"; // Load the appointments page
    }

    /**
     * Process editing an appointment, checking that none of the attendees have other appointments during this time
     *
     * @param creator the creator of the appointment
     * @return the view to display
     */
    public String doEditAppointment(Client creator) {
        // TODO validation
        if (creator == null || creator.getId() == null) { // Temp
            creator = cS.getClient("adminadmin");
        }
        this.editingAppointment.setCreator(creator); // Set the creator for this appointment
        this.editingAppointment.setAttendees(this.convertClientNamesToClients(this.attendeeUsers));

        // Try to edit the appointment
        try {
            aS.editAppointment(this.editingAppointment);
        } catch (AppointmentClashException ex) {
            Logger.getLogger(AppointmentController.class.getName()).log(Level.SEVERE, null, ex);
            this.addError("newAppointmentForm:attendees", ex.getMessage());
            return "createEditAppointment"; // Send the user back to the createEditAppointment view
        }
        this.addInfo("infoMsg", "Appointment edited", "Appointment edited");
        this.clearEditingAppointment(); // Reset the appointment
        return "appointments?faces-redirect=true"; // Load the appointments page
    }

    /**
     * Convert an array of client usernames to client objects
     *
     * @param userNames a list of client usernames
     * @return a list of client objects
     */
    public List<Client> convertClientNamesToClients(List<String> userNames) {
        ArrayList<Client> clients = new ArrayList<>();

        for (String userName : userNames) { // For each user
            clients.add(cS.getClient(userName)); // Get them and add them to the array of clients
        }

        return clients;
    }

    /**
     * Convert an array of client objects to client usernames
     *
     * @param clients a list of client objects
     * @return a list of client usernames
     */
    public List<String> convertClientsToClientNames(List<Client> clients) {
        ArrayList<String> names = new ArrayList<>();

        for (Client client : clients) { // For each user
            names.add(client.toString());
        }

        return names;
    }

    /**
     * Get all appointments held by the system
     *
     * @return a list of all appointments held by the system
     */
    public List<Appointment> getAllAppointments() {
        return aS.getAll();
    }

    /**
     * Load the edit appointment view with the specified appointment
     *
     * @param appointment the appointment to edit
     * @return the view to display
     */
    public String goToEditAppointment(Appointment appointment) {
        this.setEditingAppointment(appointment);
        this.setAttendeeUsers(this.convertClientsToClientNames(this.editingAppointment.getAttendees()));
        return "createEditAppointment";
    }

    /**
     * Search for appointments for a specified client's username
     *
     * @param searchText the username of the client who's appointments to search for
     * @return the view to display
     */
    public String doSearchAppointment(String searchText) {
        // TODO: Validate inputs
        this.setSearchClient(searchText);
        return ""; // Reload the same page
    }

    /**
     * Search for appointments by day
     *
     * @return the view to display
     */
    public String doSearchAppointment() {
        this.setSearchResults(aS.searchAppointment(this.searchDay));
        return ""; // Reload the same page
    }

    /**
     * Cancel an appointment
     *
     * @param a the appointment to cancel
     * @param page the view that this method call originated from
     * @return the view to display
     */
    public String doCancelAppointment(Appointment a, String page) {
        aS.removeAppointment(a);
        this.clearEditingAppointment();
        if (page.equals("day")) { // If we're on the day page
            this.doSearchAppointment(); // Re-search by day
        }
        this.doSearchAppointment(this.searchClient); // Otherwise re-search by user
        return ""; // Reload the same page
    }

    /**
     * Load the view to view an appointment
     *
     * @param a the appointment to view
     * @return the view to display
     */
    public String goToViewAppointment(Appointment a) {
        this.setEditingAppointment(a);
        return "viewAppointment"; // Go to the view page
    }

    /**
     * Get the corresponding facade object for this controller
     *
     * @return the corresponding facade object for this controller
     */
    @Override
    public AppointmentFacade getFacade() {
        return aF;
    }

}
