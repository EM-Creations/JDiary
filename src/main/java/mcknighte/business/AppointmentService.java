package mcknighte.business;

import java.util.Date;
import mcknighte.entity.Appointment;
import mcknighte.persistence.AppointmentFacade;
import mcknighte.persistence.ClientFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import mcknighte.entity.Client;
import mcknighte.exception.AppointmentClashException;

/**
 * AppointmentService
 *
 * @author Edward McKnight (UP608985)
 */
@Stateless
public class AppointmentService {

    @EJB
    private AppointmentFacade aF;
    @EJB
    private ClientFacade cF;

    /**
     * Edit appointment
     *
     * @param appointment Appointment
     * @return Appointment
     * @throws mcknighte.exception.AppointmentClashException
     */
    public Appointment editAppointment(Appointment appointment) throws AppointmentClashException {
        if (!this.checkAttendeesFree(appointment)) { // If all of the attendees are not free
            throw new AppointmentClashException(appointment);
        }

        aF.edit(appointment);
        return appointment;
    }

    /**
     * Get appointment
     *
     * @param appointment Appointment
     * @return Appointment
     */
    public Appointment getAppointment(Appointment appointment) {
        return aF.find(appointment.getId());
    }

    /**
     * Create appointment
     *
     * @param appointment Appointment
     * @return Appointment
     * @throws mcknighte.exception.AppointmentClashException
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
     * Remove appointment
     *
     * @param appointment Appointment
     * @return Appointment
     */
    public Appointment removeAppointment(Appointment appointment) {
        aF.remove(appointment);
        return appointment;
    }

    /**
     * Search for appointments based on client
     *
     * @param c Client
     * @return List
     */
    public List<Appointment> searchAppointment(Client c) {
        return aF.search(c);
    }

    /**
     * Search for appointments based on date
     *
     * @param d Date
     * @return List
     */
    public List<Appointment> searchAppointment(Date d) {
        return aF.search(d);
    }

    /**
     * Check whether all attendees of an appointment are free for the duration
     * of the appointment
     *
     * @param a Appointment
     * @return boolean
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
     * Get all appointments
     *
     * @return List
     */
    public List<Appointment> getAll() {
        return aF.findAll();
    }

}
