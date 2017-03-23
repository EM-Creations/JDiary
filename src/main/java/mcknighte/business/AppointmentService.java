package mcknighte.business;

import java.util.Date;
import mcknighte.entity.Appointment;
import mcknighte.persistence.AppointmentFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import mcknighte.entity.Client;
import mcknighte.exception.AppointmentClashException;

/**
 * This class is the business logic for appointments
 *
 * @author Edward McKnight (UP608985)
 * @see ClientService
 * @since 2017
 * @version 1.0
 */
@Stateless
public class AppointmentService {
    @EJB
    private AppointmentFacade aF;

    /**
     * Edit a given appointment
     *
     * @param appointment the appointment to edit
     * @return the appointment which has been edited
     * @throws mcknighte.exception.AppointmentClashException if any of the attendees are not free at this time
     */
    public Appointment editAppointment(Appointment appointment) throws AppointmentClashException {
        if (!this.checkAttendeesFree(appointment)) { // If all of the attendees are not free
            throw new AppointmentClashException(appointment);
        }

        aF.edit(appointment);
        return appointment;
    }

    /**
     * Get an appointment from an Appointment object
     *
     * @param appointment the appointment to get
     * @return the appointment, if it was found; otherwise null
     */
    public Appointment getAppointment(Appointment appointment) {
        return aF.find(appointment.getId());
    }

    /**
     * Create a new appointment
     *
     * @param appointment the appointment to persist
     * @return the appointment that has been persisted
     * @throws mcknighte.exception.AppointmentClashException if any of the attendees are not free at this time
     */
    public Appointment createAppointment(Appointment appointment) throws AppointmentClashException {
        if (!this.checkAttendeesFree(appointment)) { // If all of the attendees are not free
            throw new AppointmentClashException(appointment);
        }

        // If all of the attendees are free, continue
        aF.create(appointment);
        return appointment;
    }

    /**
     * Remove an appointment
     *
     * @param appointment the appointment to remove
     * @return the removed appointment
     */
    public Appointment removeAppointment(Appointment appointment) {
        aF.remove(appointment);
        return appointment;
    }

    /**
     * Search for appointments which are being attended by the given client
     *
     * @param c the client who's appointments are being searched for
     * @return a list of all the appointments that this client is an attendee of; otherwise null
     */
    public List<Appointment> searchAppointment(Client c) {
        return aF.search(c);
    }

    /**
     * Search for appointments which are starting on a given day
     *
     * @param d the day the appointment is starting
     * @return a list of all the appointments starting on the given day; otherwise null
     */
    public List<Appointment> searchAppointment(Date d) {
        return aF.search(d);
    }

    /**
     * Check whether all attendees of an appointment are free for the duration
     * of the appointment
     *
     * @param a the appointment to check that the attendees will be free to attend
     * @return whether or not all of the attendees will be free to attend this appointment
     */
    private boolean checkAttendeesFree(Appointment a) {
        // Loop through the attendees and check that they're all free during this appointment
        for (Client c : a.getAttendees()) { // For each attendee
            if (aF.search(c, a) != null) { // If this client already has an appointment during this time
                return false;
            }
        }

        return true;
    }

    /**
     * Get all appointments held by the system
     *
     * @return a list of all appointments held by the system
     */
    public List<Appointment> getAll() {
        return aF.findAll();
    }

}
