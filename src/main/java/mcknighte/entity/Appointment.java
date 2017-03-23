package mcknighte.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.TIMESTAMP;
import javax.validation.constraints.NotNull;
import mcknighte.common.Convertable;

/**
 * Appointment entity class, to represent an Appointment within the database
 * and throughout the application
 *
 * @author Edward McKnight (UP608985)
 * @see Client
 * @see AppointmentFacade
 * @see AppointmentService
 * @see AppointmentController
 * @since 2017
 * @version 1.0
 */
@Entity
public class Appointment implements Serializable, Convertable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Temporal(TIMESTAMP)
    private Calendar startTime;
    @NotNull
    @Temporal(TIMESTAMP)
    private Calendar endTime;
    @NotNull
    private String description;
    @NotNull
    @ManyToOne(targetEntity = Client.class)
    private Client creator;
    @NotNull
    @OneToMany(targetEntity = Client.class)
    private List<Client> attendees;

    /**
     * Constructor
     */
    public Appointment() {
    }

    /**
     * Get the attendees for the appointment
     *
     * @return a list of attendees for the appointment
     */
    public List<Client> getAttendees() {
        return attendees;
    }

    /**
     * Set the attendees for the appointment
     *
     * @param attendees a list of attendees for the appointment
     */
    public void setAttendees(List<Client> attendees) {
        this.attendees = attendees;
    }

    /**
     * Get the creator of the appointment
     *
     * @return the creator of the appointment
     */
    public Client getCreator() {
        return creator;
    }

    /**
     * Set the creator of the appointment
     *
     * @param creator the creator of the appointment
     */
    public void setCreator(Client creator) {
        this.creator = creator;
    }

    /**
     * Get the description for the appointment
     *
     * @return the description for the appointment
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description for the appointment
     *
     * @param description the description for the appointment
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the end time of the appointment as a Date object
     *
     * @return the end time of the appointment
     */
    public Date getEndTime() {
        return endTime.getTime();
    }

    /**
     * Set the end time of the appointment
     *
     * @param end the end time of the appointment
     */
    public void setEndTime(Date end) {
        this.endTime.setTime(end);
    }

    /**
     * Get the start time of the appointment as a Date object
     *
     * @return the start time of the appointment
     */
    public Date getStartTime() {
        return startTime.getTime();
    }

    /**
     * Set the start time of the appointment
     *
     * @param start the start time of the appointment
     */
    public void setStartTime(Date start) {
        this.startTime.setTime(start);
    }

    /**
     * Get the ID for the appointment
     *
     * @return the ID for the appointment
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Set the ID for the appointment
     *
     * @param id the ID for the appointment
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Hash code
     *
     * @return int
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Equals
     *
     * @param object the object to compare to
     * @return whether or not this equals the object being compared to
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Appointment)) {
            return false;
        }
        Appointment other = (Appointment) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    /**
     * Represent this object as a string
     * 
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        return "mcknighte.entity.Appointment[ id=" + id + " ]";
    }

}
