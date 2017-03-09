package com.emcreations.courseworkup608985.business;

import com.emcreations.courseworkup608985.entity.Appointment;
import com.emcreations.courseworkup608985.persistence.AppointmentFacade;
import com.emcreations.courseworkup608985.entity.Client;
import com.emcreations.courseworkup608985.persistence.ClientFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
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
     */
    public Appointment editAppointment(Appointment appointment) {
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
     */
    public Appointment createAppointment(Appointment appointment) {
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
     * Get all appointments
     * 
     * @return List
     */
    public List<Appointment> getAll() {
        return aF.findAll();
    }
    
}
