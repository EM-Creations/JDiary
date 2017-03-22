package mcknighte.entity;

import java.io.Serializable;
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
 * Appointment entity class
 * 
 * @author Edward McKnight (UP608985)
 */
@Entity
public class Appointment implements Serializable, Convertable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Temporal(TIMESTAMP)
    private Date startTime;
    @NotNull
    @Temporal(TIMESTAMP)
    private Date endTime;
    @NotNull
    private String description;
    @NotNull
    @ManyToOne(targetEntity=Client.class)
    private Client creator;
    @NotNull
    @OneToMany(targetEntity=Client.class)
    private List<Client> attendees;
    
    /**
     * Constructor
     */
    public Appointment() {
    }

    /**
     * Get the value of attendees
     *
     * @return the value of attendees
     */
    public List<Client> getAttendees() {
        return attendees;
    }

    /**
     * Set the value of attendees
     *
     * @param attendees new value of attendees
     */
    public void setAttendees(List<Client> attendees) {
        this.attendees = attendees;
    }

    /**
     * Get the value of creator
     *
     * @return the value of creator
     */
    public Client getCreator() {
        return creator;
    }

    /**
     * Set the value of creator
     *
     * @param creator new value of creator
     */
    public void setCreator(Client creator) {
        this.creator = creator;
    }

    /**
     * Get the value of description
     *
     * @return the value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the value of description
     *
     * @param description new value of description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the value of end
     *
     * @return the value of end
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * Set the value of end
     *
     * @param end new value of end
     */
    public void setEndTime(Date end) {
        this.endTime = end;
    }

    /**
     * Get the value of start
     *
     * @return the value of start
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Set the value of start
     *
     * @param start new value of start
     */
    public void setStartTime(Date start) {
        this.startTime = start;
    }

    /**
     * Get ID
     * 
     * @return Long 
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Set ID
     * 
     * @param id Long
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
     * @param object Object
     * @return boolean
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appointment)) {
            return false;
        }
        Appointment other = (Appointment) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    /**
     * To string
     */
    @Override
    public String toString() {
        return "mcknighte.entity.Appointment[ id=" + id + " ]";
    }
    
}
