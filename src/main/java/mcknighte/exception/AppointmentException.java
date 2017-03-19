package mcknighte.exception;

import mcknighte.entity.Appointment;

/**
 * AppointmentException
 * 
 * @author Edward McKnight (UP608985)
 */
public abstract class AppointmentException extends Exception {
    protected Appointment appointment;
    
    /**
     * Constructor
     * @param appointment Appointment
     */
    public AppointmentException(Appointment appointment) {
        super();
        this.appointment = appointment;
    }
}
