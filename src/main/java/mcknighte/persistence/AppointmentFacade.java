package mcknighte.persistence;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import mcknighte.common.AbstractFacade;
import mcknighte.entity.Appointment;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import mcknighte.entity.Client;

/**
 * Appointment facade
 *
 * @author Edward McKnight (UP608985)
 */
@Stateless
public class AppointmentFacade extends AbstractFacade<Appointment> {

    @PersistenceContext(unitName = "mcknighte_war_1.0PU")
    private EntityManager em;

    /**
     * Get entity manager
     *
     * @return EntityManager
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Constructor
     */
    public AppointmentFacade() {
        super(Appointment.class);
    }

    /**
     * Search for appointments by client
     *
     * @param c Client
     * @return List
     */
    public List<Appointment> search(Client c) {
        List<Appointment> appointments;

        appointments = this.getEntityManager().createQuery(
                "SELECT a FROM Appointment a WHERE :client MEMBER OF a.attendees")
                .setParameter("client", c)
                .getResultList();

        if (appointments.isEmpty()) // If the list is empty
            return null; // Return null - no appointments found
        return appointments; // Otherwise return the list of appointments
    }

    /**
     * Search for appointments by day
     *
     * @param day Date
     * @return List
     */
    public List<Appointment> search(Date day) {
        List<Appointment> appointments;
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        cal.add(Calendar.DATE, 1);

        appointments = this.getEntityManager().createQuery(
                "SELECT a FROM Appointment a WHERE a.startTime >= :day AND a.startTime < :dayAfter")
                .setParameter("day", day, TemporalType.DATE)
                .setParameter("dayAfter", cal.getTime(), TemporalType.DATE)
                .getResultList();

        if (appointments.isEmpty()) // If the list is empty
            return null; // Return null - no appointments found
        return appointments; // Otherwise return the list of appointments
    }

    /**
     * Search for appointments for a specific user between a start and end time
     *
     * @param c Client
     * @param app Appointment
     * @return List
     */
    public List<Appointment> search(Client c, Appointment app) {
        List<Appointment> appointments;

        if (app.getId() != null) { // Searching for an existing appointment
            appointments = this.getEntityManager().createQuery(
                    "SELECT a FROM Appointment a WHERE (a.startTime < :end) AND (a.endTime > :start) AND :client MEMBER OF a.attendees AND a != :appointment")
                    .setParameter("start", app.getStartTime(), TemporalType.TIMESTAMP)
                    .setParameter("end", app.getEndTime(), TemporalType.TIMESTAMP)
                    .setParameter("client", c)
                    .setParameter("appointment", app)
                    .getResultList();
        } else { // Searching for a new appointment
            appointments = this.getEntityManager().createQuery(
                    "SELECT a FROM Appointment a WHERE (a.startTime < :end) AND (a.endTime > :start) AND :client MEMBER OF a.attendees")
                    .setParameter("start", app.getStartTime(), TemporalType.TIMESTAMP)
                    .setParameter("end", app.getEndTime(), TemporalType.TIMESTAMP)
                    .setParameter("client", c)
                    .getResultList();
        }

        if (appointments.isEmpty()) // If the list is empty
            return null; // Return null - no appointments found
        return appointments; // Otherwise return the list of appointments
    }

}
