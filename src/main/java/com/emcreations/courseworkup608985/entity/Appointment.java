package com.emcreations.courseworkup608985.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.TIMESTAMP;

/**
 *
 * @author Edward McKnight (UP608985)
 */
@Entity
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TIMESTAMP)
    private Calendar startTime;
    @Temporal(TIMESTAMP)
    private Calendar endTime;
    private String description;
    @ManyToOne(targetEntity=Client.class)
    private Client creator;
    @OneToMany(targetEntity=Client.class)
    private List<?> attendees;
    
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
    public List<?> getAttendees() {
        return attendees;
    }

    /**
     * Set the value of attendees
     *
     * @param attendees new value of attendees
     */
    public void setAttendees(List<?> attendees) {
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
    public Calendar getEnd() {
        return endTime;
    }

    /**
     * Set the value of end
     *
     * @param end new value of end
     */
    public void setEnd(Calendar end) {
        this.endTime = end;
    }

    /**
     * Get the value of start
     *
     * @return the value of start
     */
    public Calendar getStart() {
        return startTime;
    }

    /**
     * Set the value of start
     *
     * @param start new value of start
     */
    public void setStart(Calendar start) {
        this.startTime = start;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appointment)) {
            return false;
        }
        Appointment other = (Appointment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.emcreations.courseworkup608985.entity.Appointment[ id=" + id + " ]";
    }
    
}
