package mcknighte.exception;

import mcknighte.entity.Appointment;

/**
 * AppointmentException, abstract exception class for appointment 
 * related exceptions
 *
 * @author Edward McKnight (UP608985)
 * @since 2017
 * @version 1.0
 */
public abstract class AppointmentException extends Exception {
    protected Appointment appointment;

    /**
     * Constructor
     *
     * @param appointment the appointment which this exception has been thrown for
     */
    public AppointmentException(Appointment appointment) {
        super();
        this.appointment = appointment;
    }
}
