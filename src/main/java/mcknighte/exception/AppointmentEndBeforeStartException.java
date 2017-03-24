package mcknighte.exception;

import mcknighte.entity.Appointment;

/**
 * AppointmentEndBeforeStartException, exception thrown for when an appointment
 * ends before it starts
 *
 * @author Edward McKnight (UP608985)
 * @see AppointmentException
 * @since 2017
 * @version 1.0
 */
public class AppointmentEndBeforeStartException extends AppointmentException {

    /**
     * Constructor
     *
     * @param appointment the appointment which this exception has been thrown for
     */
    public AppointmentEndBeforeStartException(Appointment appointment) {
        super(appointment);
    }

    /**
     * Get the message generated for this exception
     *
     * @return the message
     */
    @Override
    public String getMessage() {
        return "End time cannot be before start time.";
    }
}
