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

    public Appointment editAppointment(Appointment appointment) {
        aF.edit(appointment);
        return appointment;
    }

    public Appointment getAppointment(Appointment appointment) {
        return aF.find(appointment.getId());
    }

    public Appointment createAppointment(Appointment appointment) {
        aF.create(appointment);
        return appointment;
    }

    public Appointment removeAppointment(Appointment appointment) {
        aF.remove(appointment);
        return appointment;
    }

    public List<Appointment> getAll() {
        return aF.findAll();
    }
    
}
