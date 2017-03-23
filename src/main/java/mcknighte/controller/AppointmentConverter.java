package mcknighte.controller;

import javax.faces.convert.FacesConverter;
import mcknighte.common.AbstractConverter;
import mcknighte.entity.Appointment;
import mcknighte.persistence.AppointmentFacade;

/**
 * AppointmentConverter class to handle conversion to/from Appointment objects
 *
 * @author Edward McKnight (UP608985)
 * @see AppointmentController
 * @see AbstractConverter
 * @since 2017
 * @version 1.0
 */
@FacesConverter(forClass = Appointment.class)
public class AppointmentConverter extends AbstractConverter<Appointment, AppointmentFacade, AppointmentController> {

    /**
     * Constructor
     *
     * @param entityClass the corresponding entity class
     * @param facade the corresponding facade class
     * @param controller the corresponding controller class
     */
    public AppointmentConverter(Class<Appointment> entityClass, Class<AppointmentFacade> facade, Class<AppointmentController> controller) {
        super(entityClass, facade, controller);
    }
}
