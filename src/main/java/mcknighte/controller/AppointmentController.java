package mcknighte.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import mcknighte.business.AppointmentService;
import mcknighte.business.ClientService;
import mcknighte.common.AbstractController;
import mcknighte.entity.Appointment;
import mcknighte.entity.Client;
import mcknighte.persistence.AppointmentFacade;

/**
 * AppointmentController
 *
 * @author Edward McKnight (UP608985)
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
     * Get the appointments for a specific day of this month
     * 
     * @param day int
     * @return List
     */
    public List<Appointment> getAppointmentsForDay(int day) {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        final Date date = calendar.getTime();
        
        return aS.searchAppointment(date);
    }

    /**
     * Get the value of searchResults
     *
     * @return the value of searchResults
     */
    public List<Appointment> getSearchResults() {
        return searchResults;
    }

    /**
     * Set the value of searchResults
     *
     * @param searchResults new value of searchResults
     */
    public void setSearchResults(List<Appointment> searchResults) {
        this.searchResults = searchResults;
    }


    /**
     * Get the value of searchDay
     *
     * @return the value of searchDay
     */
    public Date getSearchDay() {
        return searchDay;
    }

    /**
     * Set the value of searchDay
     *
     * @param searchDay new value of searchDay
     */
    public void setSearchDay(Date searchDay) {
        this.searchDay = searchDay;
    }


    /**
     * Get the value of searchClient
     *
     * @return the value of searchClient
     */
    public String getSearchClient() {
        return searchClient;
    }

    /**
     * Set the value of searchClient
     *
     * @param searchClient new value of searchClient
     */
    public void setSearchClient(String searchClient) {
        this.searchClient = searchClient;
    }


    /**
     * Get the value of attendeeUsers
     *
     * @return the value of attendeeUsers
     */
    public List<String> getAttendeeUsers() {
        return attendeeUsers;
    }

    /**
     * Set the value of attendeeUsers
     *
     * @param attendeeUsers new value of attendeeUsers
     */
    public void setAttendeeUsers(List<String> attendeeUsers) {
        this.attendeeUsers = attendeeUsers;
    }
    
    /**
     * Constructor
     */
    public AppointmentController() {
        super(Appointment.class);
        this.editingAppointment = new Appointment(); // Instantiate new appointment object
        this.attendeeUsers = new ArrayList<>();
    }

    /**
     * Clear data concerning the appointment currently being edited
     *
     * @return Appointment
     */
    public Appointment clearEditingAppointment() {
        this.editingAppointment = new Appointment(); // Instantiate new appointment object (clears any existing data)
        this.attendeeUsers.clear();
        return this.editingAppointment;
    }
    
    /**
     * Get the value of editingAppointment
     *
     * @return the value of editingAppointment
     */
    public Appointment getEditingAppointment() {
        return editingAppointment;
    }

    /**
     * Set the value of editingAppointment
     *
     * @param editingAppointment new value of editingAppointment
     */
    public void setEditingAppointment(Appointment editingAppointment) {
        this.editingAppointment = editingAppointment;
    }

    /**
     * Process creating an appointment
     *
     * @param creator Client
     * @return String
     */
    public String doCreateAppointment(Client creator) {
        // TODO validation
        if (creator == null || creator.getId() == null) { // Temp
            creator = cS.getClient("adminadmin");
        }
        this.editingAppointment.setCreator(creator); // Set the creator for this appointment
        this.editingAppointment.setAttendees(this.convertClientNamesToClients(this.attendeeUsers));
        aS.createAppointment(this.editingAppointment);
        this.clearEditingAppointment(); // Reset the appointment
        return "appointments"; // Load the appointments page
    }
    
    /**
     * Process editing an appointment
     *
     * @param creator Client
     * @return String
     */
    public String doEditAppointment(Client creator) {
        // TODO validation
        if (creator == null || creator.getId() == null) { // Temp
            creator = cS.getClient("adminadmin");
        }
        this.editingAppointment.setCreator(creator); // Set the creator for this appointment
        this.editingAppointment.setAttendees(this.convertClientNamesToClients(this.attendeeUsers));
        aS.editAppointment(this.editingAppointment);
        this.clearEditingAppointment(); // Reset the appointment
        return "appointments"; // Load the appointments page
    }
    
    /**
     * Convert an array of client user names to client objects
     * 
     * @param userNames List
     * @return List
     */
    public List<Client> convertClientNamesToClients(List<String> userNames) {
        ArrayList<Client> clients = new ArrayList<>();
        
        for (String userName : userNames) { // For each user
            clients.add(cS.getClient(userName)); // Get them and add them to the array of clients
        }
        
        return clients;
    }
    
    /**
     * Convert an array of client user names to client objects
     * 
     * @param clients List
     * @return List
     */
    public List<String> convertClientsToClientNames(List<Client> clients) {
        ArrayList<String> names = new ArrayList<>();
        
        for (Client client : clients) { // For each user
            names.add(client.toString());
        }
        
        return names;
    }
    
    /**
     * Get all appointments
     *
     * @return List
     */
    public List<Appointment> getAllAppointments() {
        return aS.getAll();
    }
    
    /**
     * Do edit appointment
     * 
     * @param appointment Appointment
     * @return String
     */
    public String doEditAppointment(Appointment appointment) {
        this.setEditingAppointment(appointment);
        this.setAttendeeUsers(this.convertClientsToClientNames(this.editingAppointment.getAttendees()));
        return "createEditAppointment";
    }
    
    /**
     * Search for appointments by client
     *
     * @param searchText String
     * @return String
     */
    public String doSearchAppointment(String searchText) {
        // TODO: Validate inputs
        this.setSearchClient(searchText);
        this.setSearchResults(aS.searchAppointment(cS.getClient(searchText)));
        return ""; // Reload the same page
    }
    
    /**
     * Search for appointments by day
     *
     * @return String
     */
    public String doSearchAppointment() {
        this.setSearchResults(aS.searchAppointment(this.searchDay));
        return ""; // Reload the same page
    }
    
    /**
     * Cancel an appointment
     *
     * @param a Appointment
     * @return String
     */
    public String doCancelAppointment(Appointment a) {
        // TODO: Refresh the view properly
        aS.removeAppointment(a);
        this.clearEditingAppointment();
        return ""; // Reload the same page
    }
    
    /**
     * Load the view to view an appointment
     *
     * @param a Appointment
     * @return String
     */
    public String goToViewAppointment(Appointment a) {
        this.setEditingAppointment(a);
        return "viewAppointment"; // Go to the view page
    }

    /**
     * Get the facade for this object
     * 
     * @return AppointmentFacade
     */
    @Override
    public AppointmentFacade getFacade() {
        return aF;
    }

}
