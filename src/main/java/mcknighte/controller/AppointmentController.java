package mcknighte.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import mcknighte.business.AppointmentService;
import mcknighte.entity.Appointment;
import mcknighte.entity.Client;

/**
 * AppointmentController
 *
 * @author Edward McKnight (UP608985)
 */
@Named(value = "appointmentController")
@SessionScoped
public class AppointmentController implements Serializable {
    private static final long serialVersionUID = 1L;
    @EJB
    private AppointmentService aS;
    private Appointment editingAppointment;
    
    /**
     * Constructor
     */
    public AppointmentController() {
        this.editingAppointment = new Appointment(); // Instantiate new appointment object
    }

    /**
     * Clear data concerning the appointment currently being edited
     *
     * @return Appointment
     */
    public Appointment clearEditingAppointment() {
        this.editingAppointment = new Appointment(); // Instantiate new appointment object (clears any existing data)
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
        this.editingAppointment.setCreator(creator); // Set the creator for this appointment
        aS.createAppointment(this.editingAppointment);
        this.clearEditingAppointment(); // Reset the appointment
        return "appointments"; // Load the appointments page
    }
    
    /**
     * Get all appointments
     *
     * @return List
     */
    public List<Appointment> getAllAppointments() {
        return aS.getAll();
    }

}
