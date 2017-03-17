package mcknighte.controller;

import javax.faces.convert.FacesConverter;
import mcknighte.common.AbstractConverter;
import mcknighte.entity.Appointment;
import mcknighte.persistence.AppointmentFacade;

/**
 * AppointmentConverter
 * 
 * @author Edward McKnight (UP608985)
 */
@FacesConverter(forClass = Appointment.class)
public class AppointmentConverter extends AbstractConverter<Appointment, AppointmentFacade, AppointmentController> {
    
    /**
     * Constructor
     * 
     * @param entityClass
     * @param facade
     * @param controller 
     */
    public AppointmentConverter(Class<Appointment> entityClass, Class<AppointmentFacade> facade, Class<AppointmentController> controller) {
        super(entityClass, facade, controller);
    }
}
